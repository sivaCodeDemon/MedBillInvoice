import { Box, Button, Card, CardContent, Stack, TextField, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';

export function LoginPage() {
  const navigate = useNavigate();

  return (
    <Box
      sx={{
        minHeight: '100vh',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        background: 'linear-gradient(135deg, #e0f2fe 0%, #f5f7ff 100%)',
        p: 3,
      }}
    >
      <Card sx={{ width: 430, borderRadius: 4, p: 1, boxShadow: '0 24px 50px rgba(61, 90, 128, 0.16)' }}>
        <CardContent sx={{ p: 4 }}>
          <Stack spacing={3}>
            <Box>
              <Typography variant="overline" color="primary.main" sx={{ fontWeight: 700 }}>MedBill Pro</Typography>
              <Typography variant="h4" sx={{ fontWeight: 700 }}>Sign in</Typography>
            </Box>

            <TextField label="Email or username" defaultValue="admin@medbill.local" fullWidth />
            <TextField label="Password" type="password" defaultValue="admin123" fullWidth />

            <Button
              variant="contained"
              size="large"
              onClick={() => navigate('/dashboard')}
              sx={{ borderRadius: 3, py: 1.5, fontWeight: 700 }}
            >
              Login
            </Button>

            <Typography variant="body2" color="text.secondary">
              Secure access for hospital admins, billing managers, and cashiers.
            </Typography>
          </Stack>
        </CardContent>
      </Card>
    </Box>
  );
}
