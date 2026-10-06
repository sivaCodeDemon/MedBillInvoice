import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  timeout: 5000,
});

export const fetchDashboardSummary = async () => {
  const response = await apiClient.get('/dashboard');
  return response.data;
};
