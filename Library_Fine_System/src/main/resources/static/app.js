/* ============================================================
   Library Fine System — Frontend JavaScript (app.js)
   ============================================================ */

const API = '';   // same-origin: Spring Boot serves both frontend and backend

/* ============================================================
   NAVIGATION
   ============================================================ */
document.querySelectorAll('.nav-item').forEach(item => {
    item.addEventListener('click', () => {
        document.querySelectorAll('.nav-item').forEach(n => n.classList.remove('active'));
        document.querySelectorAll('.page').forEach(p => p.classList.remove('active'));
        item.classList.add('active');
        document.getElementById('page-' + item.dataset.page).classList.add('active');
        // Trigger page-specific data load
        switch (item.dataset.page) {
            case 'dashboard': loadDashboard(); break;
            case 'books':     loadBooks();     break;
            case 'members':   loadMembers();   break;
            case 'loans':     loadActiveLoans(); break;
            case 'fines':     loadFinesPage(); break;
        }
    });
});

/* ============================================================
   UTILITIES
   ============================================================ */
function toast(msg, type = 'info') {
    const container = document.getElementById('toast-container');
    const el = document.createElement('div');
    const icons = { success: '✅', error: '❌', info: 'ℹ️', warning: '⚠️' };
    el.className = `toast ${type}`;
    el.innerHTML = `<span>${icons[type] || 'ℹ️'}</span> ${msg}`;
    container.appendChild(el);
    setTimeout(() => el.remove(), 3200);
}

function fmt(date) {
    if (!date) return '—';
    return new Date(date).toLocaleDateString('en-IN', { day: '2-digit', month: 'short', year: 'numeric' });
}

function fmtFine(amount) {
    if (!amount || amount === 0) return `<span class="fine-amount fine-zero">$0.00</span>`;
    return `<span class="fine-amount fine-positive">$${Number(amount).toFixed(2)}</span>`;
}

function statusBadge(status) {
    const map = { ACTIVE: 'status-active', RETURNED: 'status-returned', OVERDUE: 'status-overdue' };
    return `<span class="badge-status ${map[status] || ''}">${status}</span>`;
}

function copiesBar(available, total) {
    const dots = Array.from({ length: total }, (_, i) =>
        `<span class="copy-dot ${i < available ? 'available' : ''}"></span>`
    ).join('');
    return `<div class="copies-bar"><div class="copies-dots">${dots}</div> ${available}/${total}</div>`;
}

async function apiFetch(path, opts = {}) {
    const res = await fetch(API + path, {
        headers: { 'Content-Type': 'application/json' },
        ...opts
    });
    if (!res.ok) {
        const err = await res.json().catch(() => ({ message: res.statusText }));
        throw new Error(err.message || 'Request failed');
    }
    // 204 No Content
    if (res.status === 204) return null;
    return res.json();
}

/* ============================================================
   DASHBOARD
   ============================================================ */
async function loadDashboard() {
    try {
        const [books, members, loans] = await Promise.all([
            apiFetch('/api/books'),
            apiFetch('/api/members'),
            apiFetch('/api/loans/all')
        ]);

        document.getElementById('stat-books').textContent   = books.length;
        document.getElementById('stat-members').textContent = members.length;

        const active  = loans.filter(l => l.status === 'ACTIVE');
        const overdue = loans.filter(l => l.status === 'OVERDUE');
        document.getElementById('stat-active-loans').textContent = active.length;
        document.getElementById('stat-overdue').textContent      = overdue.length;

        const totalFines = members.reduce((s, m) => s + (m.totalPendingFines || 0), 0);
        document.getElementById('stat-fines').textContent = '$' + totalFines.toFixed(2);

        // Recent 6 loans
        const recent = [...loans].reverse().slice(0, 6);
        const tbody = document.getElementById('dash-loans-body');
        if (recent.length === 0) {
            tbody.innerHTML = `<tr><td colspan="6"><div class="empty-state"><p>No loan history yet.</p></div></td></tr>`;
        } else {
            tbody.innerHTML = recent.map(l => `
                <tr>
                    <td>${l.book?.title ?? `Book #${l.book?.id}`}</td>
                    <td>${l.member?.name ?? `Member #${l.member?.id}`}</td>
                    <td>${fmt(l.issueDate)}</td>
                    <td>${fmt(l.dueDate)}</td>
                    <td>${statusBadge(l.status)}</td>
                    <td>${fmtFine(l.fineAmount)}</td>
                </tr>`).join('');
        }
    } catch (e) {
        toast('Failed to load dashboard: ' + e.message, 'error');
    }
}

/* ============================================================
   BOOKS
   ============================================================ */
let allBooks = [];

async function loadBooks() {
    try {
        allBooks = await apiFetch('/api/books');
        renderBooks(allBooks);
    } catch (e) {
        toast('Failed to load books: ' + e.message, 'error');
    }
}

function renderBooks(books) {
    const tbody = document.getElementById('books-body');
    if (books.length === 0) {
        tbody.innerHTML = `<tr><td colspan="5"><div class="empty-state"><div class="icon">📭</div><p>No books found.</p></div></td></tr>`;
        return;
    }
    tbody.innerHTML = books.map(b => `
        <tr>
            <td><code>${b.id}</code></td>
            <td><strong>${b.title}</strong></td>
            <td>${b.author}</td>
            <td><span style="background:rgba(99,102,241,0.12);padding:2px 8px;border-radius:20px;font-size:0.78rem;color:#818cf8">${b.category}</span></td>
            <td>${copiesBar(b.availableCopies, b.totalCopies)}</td>
        </tr>`).join('');
}

function filterBooks() {
    const q = document.getElementById('filter-category').value.toLowerCase();
    renderBooks(q ? allBooks.filter(b => b.category.toLowerCase().includes(q)) : allBooks);
}

async function addBook() {
    const title    = document.getElementById('book-title').value.trim();
    const author   = document.getElementById('book-author').value.trim();
    const category = document.getElementById('book-category').value.trim();
    const copies   = parseInt(document.getElementById('book-copies').value);

    if (!title || !author || !category || isNaN(copies) || copies < 1) {
        toast('Please fill in all fields correctly.', 'warning');
        return;
    }

    try {
        await apiFetch('/api/books', {
            method: 'POST',
            body: JSON.stringify({ title, author, category, totalCopies: copies })
        });
        toast(`"${title}" added successfully!`, 'success');
        ['book-title','book-author','book-category','book-copies'].forEach(id => document.getElementById(id).value = '');
        loadBooks();
    } catch (e) {
        toast('Error adding book: ' + e.message, 'error');
    }
}

/* ============================================================
   MEMBERS
   ============================================================ */
async function loadMembers() {
    try {
        const members = await apiFetch('/api/members');
        renderMembers(members);
    } catch (e) {
        toast('Failed to load members: ' + e.message, 'error');
    }
}

function renderMembers(members) {
    const tbody = document.getElementById('members-body');
    if (members.length === 0) {
        tbody.innerHTML = `<tr><td colspan="6"><div class="empty-state"><div class="icon">👤</div><p>No members found.</p></div></td></tr>`;
        return;
    }
    tbody.innerHTML = members.map(m => `
        <tr>
            <td><code>${m.id}</code></td>
            <td><strong>${m.name}</strong></td>
            <td>${m.email}</td>
            <td>${m.phoneNumber}</td>
            <td>${fmtFine(m.totalPendingFines)}</td>
            <td>
                ${m.totalPendingFines > 0
                    ? `<button class="btn btn-warning btn-sm" onclick="payFine(${m.id}, '${m.name}')">💳 Pay Fine</button>`
                    : `<span style="color:var(--text-muted);font-size:0.8rem">No fines</span>`}
            </td>
        </tr>`).join('');
}

async function registerMember() {
    const name        = document.getElementById('member-name').value.trim();
    const email       = document.getElementById('member-email').value.trim();
    const phoneNumber = document.getElementById('member-phone').value.trim();

    if (!name || !email || !phoneNumber) {
        toast('Please fill in all member fields.', 'warning');
        return;
    }

    try {
        await apiFetch('/api/members', {
            method: 'POST',
            body: JSON.stringify({ name, email, phoneNumber })
        });
        toast(`${name} registered successfully!`, 'success');
        ['member-name','member-email','member-phone'].forEach(id => document.getElementById(id).value = '');
        loadMembers();
    } catch (e) {
        toast('Error registering member: ' + e.message, 'error');
    }
}

async function payFine(memberId, name) {
    if (!confirm(`Clear all pending fines for ${name}?`)) return;
    try {
        await apiFetch(`/api/members/${memberId}/pay-fine`, { method: 'POST' });
        toast(`Fines cleared for ${name}!`, 'success');
        loadMembers();
    } catch (e) {
        toast('Error paying fine: ' + e.message, 'error');
    }
}

/* ============================================================
   LOANS
   ============================================================ */
async function loadActiveLoans() {
    try {
        const loans = await apiFetch('/api/loans');
        const tbody = document.getElementById('loans-body');
        if (loans.length === 0) {
            tbody.innerHTML = `<tr><td colspan="6"><div class="empty-state"><div class="icon">✅</div><p>No active or overdue loans.</p></div></td></tr>`;
            return;
        }
        tbody.innerHTML = loans.map(l => `
            <tr>
                <td><code>${l.id}</code></td>
                <td>${l.book?.title ?? `Book #${l.book?.id}`}</td>
                <td>${l.member?.name ?? `Member #${l.member?.id}`}</td>
                <td>${fmt(l.issueDate)}</td>
                <td>${fmt(l.dueDate)}</td>
                <td>${statusBadge(l.status)}</td>
            </tr>`).join('');
    } catch (e) {
        toast('Failed to load loans: ' + e.message, 'error');
    }
}

async function borrowBook() {
    const bookId   = parseInt(document.getElementById('borrow-bookid').value);
    const memberId = parseInt(document.getElementById('borrow-memberid').value);

    if (isNaN(bookId) || isNaN(memberId)) {
        toast('Please enter valid Book ID and Member ID.', 'warning');
        return;
    }

    try {
        const loan = await apiFetch('/api/loans/borrow', {
            method: 'POST',
            body: JSON.stringify({ bookId, memberId })
        });
        toast(`Loan #${loan.id} created! Due: ${fmt(loan.dueDate)}`, 'success');
        document.getElementById('borrow-bookid').value = '';
        document.getElementById('borrow-memberid').value = '';
        loadActiveLoans();
    } catch (e) {
        toast('Error borrowing book: ' + e.message, 'error');
    }
}

async function returnBook() {
    const loanId = parseInt(document.getElementById('return-loanid').value);
    if (isNaN(loanId)) {
        toast('Please enter a valid Loan ID.', 'warning');
        return;
    }
    try {
        const loan = await apiFetch('/api/loans/return', {
            method: 'POST',
            body: JSON.stringify({ loanId })
        });
        const fine = loan.fineAmount > 0 ? ` Fine applied: $${loan.fineAmount.toFixed(2)}` : ' No fine.';
        toast(`Book returned successfully!${fine}`, loan.fineAmount > 0 ? 'warning' : 'success');
        document.getElementById('return-loanid').value = '';
        loadActiveLoans();
    } catch (e) {
        toast('Error returning book: ' + e.message, 'error');
    }
}

async function updateOverdue() {
    try {
        await apiFetch('/api/loans/update-overdue', { method: 'POST' });
        toast('Overdue statuses synced!', 'info');
        loadActiveLoans();
    } catch (e) {
        toast('Error syncing overdue: ' + e.message, 'error');
    }
}

/* ============================================================
   FINES PAGE
   ============================================================ */
async function loadFinesPage() {
    try {
        const [loans, members] = await Promise.all([
            apiFetch('/api/loans/all'),
            apiFetch('/api/members')
        ]);

        // Loan history table
        const histBody = document.getElementById('history-body');
        if (loans.length === 0) {
            histBody.innerHTML = `<tr><td colspan="7"><div class="empty-state"><div class="icon">📋</div><p>No loan history.</p></div></td></tr>`;
        } else {
            histBody.innerHTML = [...loans].reverse().map(l => `
                <tr>
                    <td><code>${l.id}</code></td>
                    <td>${l.book?.title ?? `Book #${l.book?.id}`}</td>
                    <td>${l.member?.name ?? `Member #${l.member?.id}`}</td>
                    <td>${fmt(l.dueDate)}</td>
                    <td>${fmt(l.returnDate)}</td>
                    <td>${statusBadge(l.status)}</td>
                    <td>${fmtFine(l.fineAmount)}</td>
                </tr>`).join('');
        }

        // Members with fines table
        const fineMembers = members.filter(m => m.totalPendingFines > 0);
        const finesBody   = document.getElementById('fines-members-body');
        if (fineMembers.length === 0) {
            finesBody.innerHTML = `<tr><td colspan="5"><div class="empty-state"><div class="icon">✅</div><p>All fines are clear!</p></div></td></tr>`;
        } else {
            finesBody.innerHTML = fineMembers.map(m => `
                <tr>
                    <td><code>${m.id}</code></td>
                    <td><strong>${m.name}</strong></td>
                    <td>${m.email}</td>
                    <td>${fmtFine(m.totalPendingFines)}</td>
                    <td><button class="btn btn-warning btn-sm" onclick="payFine(${m.id}, '${m.name}')">💳 Pay Fine</button></td>
                </tr>`).join('');
        }
    } catch (e) {
        toast('Failed to load fines: ' + e.message, 'error');
    }
}

/* ============================================================
   GLOBAL REFRESH
   ============================================================ */
function refreshAll() {
    loadDashboard();
    toast('Data refreshed!', 'info');
}

/* ============================================================
   INIT — Add Jackson serialization config for lazy entities
   Response JSON has book/member as objects; handle gracefully.
   ============================================================ */
window.addEventListener('DOMContentLoaded', () => {
    loadDashboard();
});
