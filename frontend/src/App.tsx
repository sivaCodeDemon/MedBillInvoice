import {
  AppBar,
  Avatar,
  Box,
  Drawer,
  List,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Stack,
  Toolbar,
  Typography,
} from '@mui/material';
import { Navigate, Outlet, Route, Routes, useLocation, useNavigate } from 'react-router-dom';
import { DashboardPage } from './pages/DashboardPage';
import { LoginPage } from './pages/LoginPage';
import {
  Assessment,
  Dashboard,
  Description,
  MedicalServices,
  People,
  ReceiptLong,
  Security,
  Settings,
} from '@mui/icons-material';

const navItems = [
  { label: 'Dashboard', path: '/dashboard', icon: <Dashboard /> },
  { label: 'Invoices', path: '/invoices', icon: <Description /> },
  { label: 'Patients', path: '/patients', icon: <People /> },
  { label: 'Insurance', path: '/insurance', icon: <MedicalServices /> },
  { label: 'Reports', path: '/reports', icon: <Assessment /> },
  { label: 'Payments', path: '/payments', icon: <ReceiptLong /> },
  { label: 'Security', path: '/admin', icon: <Security /> },
  { label: 'Settings', path: '/settings', icon: <Settings /> },
];

function DashboardLayout() {
  const navigate = useNavigate();
  const location = useLocation();

  return (
    <Box sx={{ display: 'flex', minHeight: '100vh', bgcolor: '#f4f7fb' }}>
      <Drawer variant="permanent" sx={{ width: 260, flexShrink: 0, '& .MuiDrawer-paper': { width: 260, boxSizing: 'border-box', borderRight: '1px solid rgba(15,23,42,0.08)' } }}>
        <Toolbar>
          <Typography variant="h6" sx={{ fontWeight: 800, letterSpacing: -0.5 }}>
            MedBill Pro
          </Typography>
        </Toolbar>

        <List sx={{ px: 2 }}>
          {navItems.map((item) => (
            <ListItemButton
              key={item.label}
              selected={location.pathname === item.path}
              onClick={() => navigate(item.path)}
              sx={{ borderRadius: 2, mb: 1 }}
            >
              <ListItemIcon>{item.icon}</ListItemIcon>
              <ListItemText primary={item.label} />
            </ListItemButton>
          ))}
        </List>
      </Drawer>

      <Box sx={{ flexGrow: 1 }}>
        <AppBar
          position="sticky"
          color="transparent"
          elevation={0}
          sx={{ backdropFilter: 'blur(10px)', borderBottom: '1px solid rgba(15, 23, 42, 0.06)', bgcolor: 'rgba(255, 255, 255, 0.75)' }}
        >
          <Toolbar sx={{ justifyContent: 'space-between' }}>
            <Box>
              <Typography variant="caption" color="text.secondary">Hospital Billing Platform</Typography>
            </Box>

            <Stack direction="row" spacing={2} alignItems="center">
              <Typography variant="body2" color="text.secondary">Today: 4.8% revenue growth</Typography>
              <Avatar sx={{ bgcolor: 'primary.main' }}>MP</Avatar>
            </Stack>
          </Toolbar>
        </AppBar>

        <Outlet />
      </Box>
    </Box>
  );
}

export default function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route element={<DashboardLayout />}>
        <Route path="/" element={<Navigate to="/dashboard" replace />} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/invoices" element={<DashboardPage />} />
        <Route path="/patients" element={<DashboardPage />} />
        <Route path="/insurance" element={<DashboardPage />} />
        <Route path="/reports" element={<DashboardPage />} />
        <Route path="/payments" element={<DashboardPage />} />
        <Route path="/admin" element={<DashboardPage />} />
        <Route path="/settings" element={<DashboardPage />} />
      </Route>
    </Routes>
  );
}
