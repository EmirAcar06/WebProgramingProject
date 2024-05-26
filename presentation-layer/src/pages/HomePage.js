import React, { useState } from 'react';

import axios from 'axios';

import { useNavigate } from 'react-router-dom';

import './HomePage.css';



const HomePage = () => {

  const [city, setCity] = useState('');

  const [loading, setLoading] = useState(false);

  const [error, setError] = useState(null);

  const navigate = useNavigate();



  const handleRegisterAndFetch = async () => {

    setLoading(true);

    setError(null);

    try {

      // Check if the city is already registered

      const checkResponse = await axios.get(`http://localhost:8080/api/cities/${city}`, {

        headers: {

          'accept': '*/*'

        }

      });



      if (checkResponse.data) {

        // If city is already registered, navigate to CityDetail page

        navigate(`/city-detail/${city}`);

      } else {

        // If city is not registered, register it first

        await axios.post('http://localhost:8080/api/cities', { name: city });

        alert('City registered successfully!');

        

        // Fetch weather data
        const response = await axios.get(`http://localhost:8080/api/cities/${city}`, {

          headers: {

            'accept': '*/*'

          }

        });

        

        // If successful, navigate to CityDetail page with the city name

        navigate(`/city-detail/${city}`);

      }

    } catch (error) {

      setError('Şehir bulunamadı veya kaydedilemedi. Lütfen geçerli bir şehir adı girin.');

      console.error('Error registering or fetching city:', error);

    }

    setLoading(false);

  };



  return (

    <div className="container">

      <h1>Hava Durumu</h1>

      <p>Bu uygulama ile istediğiniz şehrin hava durumu bilgilerini öğrenebilirsiniz. Aşağıdaki alana şehir adını girin ve "Ara" butonuna tıklayın.</p>

      <div className="input-container">

        <input

          type="text"

          placeholder="Şehir Girin"

          value={city}

          onChange={(e) => setCity(e.target.value)}

        />

        <button onClick={handleRegisterAndFetch}>

          Ara

        </button>

      </div>

      {loading ? (

        <p>Yükleniyor...</p>

      ) : error ? (

        <p className="error">{error}</p>

      ) : null}

      <div className="info">

        <h2>Nasıl Çalışır?</h2>

        <p>1. Şehir adını girin ve "Ara" butonuna tıklayın.</p>

        <p>2. Şehir adı veritabanımıza kaydedilir ve hava durumu bilgileri alınır.</p>

        <p>3. Hava durumu bilgileri başarıyla alındığında, şehir detayları sayfasına yönlendirilirsiniz.</p>

        <h2>Hava Durumu Bilgileri</h2>

        <p>Şehir detayları sayfasında aşağıdaki bilgileri görebilirsiniz:</p>

        <ul>

          <li>Şehir Adı</li>

          <li>Ülke</li>

          <li>Enlem ve Boylam</li>

          <li>Maksimum ve Minimum Sıcaklık</li>

          <li>Güncel Sıcaklık</li>

          <li>Hava Durumu Açıklaması</li>

        </ul>

      </div>

    </div>

  );

};



export default HomePage;
