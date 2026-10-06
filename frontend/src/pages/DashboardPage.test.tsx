// @vitest-environment jsdom

import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/vitest';
import { beforeEach, describe, expect, it, vi } from 'vitest';

const { fetchDashboardSummary } = vi.hoisted(() => ({ fetchDashboardSummary: vi.fn() }));

vi.mock('../api/dashboard', () => ({ fetchDashboardSummary }));
vi.mock('recharts', () => ({
  Area: () => null,
  AreaChart: () => null,
  Bar: () => null,
  BarChart: () => null,
  CartesianGrid: () => null,
  ResponsiveContainer: () => null,
  Tooltip: () => null,
  XAxis: () => null,
  YAxis: () => null,
}));

import { DashboardPage } from './DashboardPage';

describe('DashboardPage', () => {
  beforeEach(() => {
    fetchDashboardSummary.mockReset();
    fetchDashboardSummary.mockResolvedValue({
      revenue: 124000,
      outstanding: 210000,
      paidPercent: 89,
      claims: 142,
      recentInvoices: [
        {
          invoiceNumber: 'INV-2026-000123',
          patientName: 'Aisha Patel',
          amount: 14500,
          status: 'PAID',
        },
      ],
    });
  });

  it('renders recent invoices and collection data returned by the dashboard API', async () => {
    render(<DashboardPage />);

    expect(await screen.findByText('INV-2026-000123')).toBeInTheDocument();
    expect(screen.getByText('Aisha Patel • ₹14,500')).toBeInTheDocument();
    expect(screen.getAllByText('89%')).toHaveLength(2);
    expect(fetchDashboardSummary).toHaveBeenCalledOnce();
  });
});
