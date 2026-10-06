import { Card, CardContent, Stack, Typography } from '@mui/material';
import type { ReactNode } from 'react';

type Props = {
  title: string;
  value: string;
  trend: string;
  accent: ReactNode;
};

export function StatCard({ title, value, trend, accent }: Props) {
  return (
    <Card sx={{ borderRadius: 3, boxShadow: '0 20px 40px rgba(15, 23, 42, 0.08)' }}>
      <CardContent>
        <Stack direction="row" justifyContent="space-between" alignItems="center">
          <Typography variant="subtitle2" color="text.secondary">{title}</Typography>
          {accent}
        </Stack>
        <Typography variant="h4" sx={{ mt: 2, fontWeight: 700 }}>{value}</Typography>
        <Typography variant="body2" sx={{ mt: 1, color: 'success.main' }}>{trend}</Typography>
      </CardContent>
    </Card>
  );
}
