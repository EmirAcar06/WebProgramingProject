# Base image olarak Node.js'in bir versiyonunu kullan
FROM node:14
# Uygulama dosyalarını içeri kopyalamak için çalışma dizini oluştur
WORKDIR /app
# package.json ve package-lock.json dosyalarını kopyala
COPY package*.json ./
# NPM ile bağımlılıkları yükle
RUN npm install
# Uygulama dosyalarını çalışma dizinine kopyala
COPY . .
# Uygulamayı build et
RUN npm run build
# Uygulamanın çalışacağı portu belirle
EXPOSE 3000
# Uygulamayı başlat
CMD ["npm", "start"]
