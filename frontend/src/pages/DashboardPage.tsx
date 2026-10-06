import { useEffect, useState } from 'react';
import {
  Box,
  Card,
  CardContent,
  Grid,
  List,
  ListItem,
  ListItemText,
  Stack,
  Typography,
} from '@mui/material';
import { Area, AreaChart, Bar, BarChart, CartesianGrid, ResponsiveContainer, Tooltip, XAxis, YAxis } from 'recharts';
import { fetchDashboardSummary } from '../api/dashboard';
import { StatCard } from '../components/StatCard';

const revenueTrend = [
  { month: 'Jan', revenue: 260000 },
  { month: 'Feb', revenue: 240000 },
  { month: 'Mar', revenue: 330000 },
  { month: 'Apr', revenue: 390000 },
  { month: 'May', revenue: 420000 },
  { month: 'Jun', revenue: 470000 },
];

const paymentMethods = [
  { name: 'Card', value: 48 },
  { name: 'UPI', value: 24 },
  { name: 'Cash', value: 10 },
  { name: 'Insurance', value: 18 },
];

export function DashboardPage() {
  const [data, setData] = useState<any>(null);

  useEffect(() => {
    fetchDashboardSummary().then(setData).catch(() => {
      setData({
        revenue: 124000,
        outstanding: 210000,
        paidPercent: 89,
        claims: 142,
        recentInvoices: [
          { invoiceNumber: 'INV-2026-000001', patientName: 'Rahul Sharma', amount: 14500, status: 'PAID' },
          { invoiceNumber: 'INV-2026-000002', patientName: 'Ananya Verma', amount: 9800, status: 'PARTIALLY_PAID' },
        ],
      });
    });
  }, []);

  const statCards = [
    { title: 'Revenue', value: '₹12.4L', trend: '+12.8% vs last month', accent: <Typography color="primary.main">↗</Typography> },
    { title: 'Outstanding', value: '₹2.1L', trend: '12 overdue invoices', accent: <Typography color="warning.main">!</Typography> },
    { title: 'Paid', value: '89%', trend: 'Collection strong', accent: <Typography color="success.main">✓</Typography> },
    { title: 'Claims', value: '142', trend: '7 pending review', accent: <Typography color="info.main">◎</Typography> },
  ];

  return (
    <Box sx={{ p: 3 }}>
      <Stack spacing={3}>
        <Box>
          <Typography variant="h4" sx={{ fontWeight: 700 }}>Billing dashboard</Typography>
          <Typography variant="body2" color="text.secondary">
            Financial overview for the current operating period
          </Typography>
        </Box>

        <Grid container spacing={3}>
          {statCards.map((card) => (
            <Grid key={card.title} size={{ xs: 12, md: 6, lg: 3 }}>
              <StatCard title={card.title} value={card.value} trend={card.trend} accent={card.accent} />
            </Grid>
          ))}
        </Grid>

        <Grid container spacing={3}>
          <Grid size={{ xs: 12, lg: 8 }}>
            <Card sx={{ borderRadius: 3 }}>
              <CardContent>
                <Typography variant="h6" sx={{ fontWeight: 600, mb: 2 }}>Revenue trend</Typography>
                <Box sx={{ height: 280 }}>
                  <ResponsiveContainer width="100%" height="100%">
                    <AreaChart data={revenueTrend}>
                      <defs>
                        <linearGradient id="revenueFill" x1="0" x2="0" y1="0" y2="1">
                          <stop offset="5%" stopColor="#4285F4" stopOpacity={0.8} />
                          <stop offset="95%" stopColor="#4285F4" stopOpacity={0.1} />
                        </linearGradient>
                      </defs>
                      <CartesianGrid strokeDasharray="3 3" vertical={false} />
                      <XAxis dataKey="month" />
                      <YAxis tickFormatter={(v) => `₹${(v / 100000).toFixed(1)}L`} />
                      <Tooltip formatter={(value) => [`₹${Number(value).toLocaleString()}`, 'Revenue']} />
                      <Area type="monotone" dataKey="revenue" stroke="#4285F4" fill="url(#revenueFill)" />
                    </AreaChart>
                  </ResponsiveContainer>
                </Box>
              </CardContent>
            </Card>
          </Grid>

          <Grid size={{ xs: 12, lg: 4 }}>
            <Card sx={{ borderRadius: 3, height: '100%' }}>
              <CardContent>
                <Typography variant="h6" sx={{ fontWeight: 600, mb: 2 }}>Payment mix</Typography>
                <Box sx={{ height: 280 }}>
                  <ResponsiveContainer width="100%" height="100%">
                    <BarChart data={paymentMethods} layout="vertical">
                      <CartesianGrid strokeDasharray="3 3" horizontal={false} />
                      <XAxis type="number" />
                      <YAxis type="category" dataKey="name" width={60} />
                      <Tooltip formatter={(value) => [`${value}%`, 'Share']} />
                      <Bar dataKey="value" fill="#3b82f6" radius={[0, 8, 8, 0]} />
                    </BarChart>
                  </ResponsiveContainer>
                </Box>
              </CardContent>
            </Card>
          </Grid>
        </Grid>

        <Grid container spacing={3}>
          <Grid size={{ xs: 12, lg: 6 }}>
            <Card sx={{ borderRadius: 3 }}>
              <CardContent>
                <Typography variant="h6" sx={{ fontWeight: 600, mb: 2 }}>Recent invoices</Typography>
                <List>
                  {(data?.recentInvoices ?? []).map((invoice: any) => (
                    <ListItem key={invoice.invoiceNumber} divider>
                      <ListItemText
                        primary={invoice.invoiceNumber}
                        secondary={`${invoice.patientName} • ₹${invoice.amount.toLocaleString()}`}
                      />
                      <Typography variant="body2" sx={{ color: invoice.status === 'PAID' ? 'success.main' : 'warning.main' }}>
                        {invoice.status}
                      </Typography>
                    </ListItem>
                  ))}
                </List>
              </CardContent>
            </Card>
          </Grid>

          <Grid size={{ xs: 12, lg: 6 }}>
            <Card sx={{ borderRadius: 3 }}>
              <CardContent>
                <Typography variant="h6" sx={{ fontWeight: 600, mb: 2 }}>Operational ratio</Typography>
                <Stack spacing={2}>
                  <Box>
                    <Typography variant="body2" color="text.secondary">Collection rate</Typography>
                    <Typography variant="h5" sx={{ fontWeight: 700 }}>{data?.paidPercent ?? 89}%</Typography>
                  </Box>
                  <Box>
                    <Typography variant="body2" color="text.secondary">Open claims</Typography>
                    <Typography variant="h5" sx={{ fontWeight: 700 }}>{data?.claims ?? 142}</Typography>
                  </Box>
                  <Box>
                    <Typography variant="body2" color="text.secondary">Receivables outstanding</Typography>
                    <Typography variant="h5" sx={{ fontWeight: 700 }}>₹{data?.outstanding?.toLocaleString?.() ?? '2,10,000'}</Typography>
                  </Box>
                </Stack>
              </CardContent>
            </Card>
          </Grid>
        </Grid>
      </Stack>
    </Box>
  );
}
