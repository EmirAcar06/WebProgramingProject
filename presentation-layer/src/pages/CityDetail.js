import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import { Card, CardContent, Typography, CircularProgress, Alert } from '@mui/material';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSun, faCloud, faCloudRain, faSnowflake, faSmog } from '@fortawesome/free-solid-svg-icons';
import './CityDetail.css';

// Fix for default icon issue with leaflet
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png',
  iconUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png',
  shadowUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png',
});

const getWeatherIcon = (description) => {
  if (!description) return faCloud; // Varsayılan simge
  switch (description.toLowerCase()) {
    case 'clear sky':
      return faSun;
    case 'few clouds':
    case 'scattered clouds':
    case 'broken clouds':
      return faCloud;
    case 'shower rain':
    case 'rain':
      return faCloudRain;
    case 'thunderstorm':
      return faCloudRain;
    case 'snow':
      return faSnowflake;
    case 'mist':
      return faSmog;
    default:
      return faCloud;
  }
};

const CityDetail = () => {
  const { city } = useParams();
  const [weatherData, setWeatherData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchWeatherData = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await axios.get(`http://localhost:8080/api/cities/${city}`, {
          headers: {
            'accept': '*/*'
          }
        });
        setWeatherData(response.data);
      } catch (error) {
        setError('Şehir bulunamadı. Lütfen geçerli bir şehir adı girin.');
        console.error('Error fetching weather data:', error);
      }
      setLoading(false);
    };

    fetchWeatherData();
  }, [city]);

  return (
    <div className="container">
      <Typography variant="h4" gutterBottom className="typography-title">
        City Details
      </Typography>
      {loading ? (
        <CircularProgress className="circular-progress" />
      ) : error ? (
        <Alert severity="error" className="alert">{error}</Alert>
      ) : weatherData ? (
        <Card className="card">
          <div className="map-container">
            <MapContainer center={[weatherData.lat, weatherData.lon]} zoom={13} style={{ height: '100%', width: '100%' }}>
              <TileLayer
                url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              />
              <Marker position={[weatherData.lat, weatherData.lon]}>
                <Popup>
                  {weatherData.name}
                </Popup>
              </Marker>
            </MapContainer>
          </div>
          <CardContent>
            <Typography variant="h5" component="div" className="typography-content">
              {weatherData.name}  {weatherData.temp}°C
            </Typography>
            <Typography variant="body2" color="text.secondary" className="typography-content">
              <strong>Country:</strong> {weatherData.country}
            </Typography>
            <Typography variant="body2" color="text.secondary" className="typography-content">
              <strong>Latitude:</strong> {weatherData.lat}
            </Typography>
            <Typography variant="body2" color="text.secondary" className="typography-content">
              <strong>Longitude:</strong> {weatherData.lon}
            </Typography>
            <Typography variant="body2" color="text.secondary" className="typography-content">
              <strong>Maximum Temperature:</strong> {weatherData.maxTemp}°C
            </Typography>
            <Typography variant="body2" color="text.secondary" className="typography-content">
              <strong>Minimum Temperature:</strong> {weatherData.minTemp}°C
            </Typography>
            
            <Typography variant="body2" color="text.secondary" className="typography-content">
              <strong>Weather Description:</strong> {weatherData.weatherDescription}
              <FontAwesomeIcon icon={getWeatherIcon(weatherData.weatherDescription)} style={{ marginLeft: '10px' }} />
            </Typography>
          </CardContent>
        </Card>
      ) : null}
    </div>
  );
};

export default CityDetail;