export type DashboardMetric = {
  title: string;
  value: string;
  trend: string;
  tone: 'positive' | 'warning' | 'info';
};

export type InvoiceOverview = {
  id: string;
  invoiceNumber: string;
  patientName: string;
  amount: number;
  status: string;
};
