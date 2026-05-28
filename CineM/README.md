# CineM — Atmosferik Film Arşivi 🎬

Mustafa Karakoyun tarafından, Spring Boot katmanlı mimarisi ve Don Norman'ın kullanıcı deneyimi (UX) ilkeleri harmanlanarak geliştirilmiş, karanlık/sinematik estetiğe sahip bir film veritabanı ve yönetim platformudur.

Proje; backend katmanında veri bütünlüğü ve performansı, frontend katmanında ise asimetrik görsel gerilim ve "keşfedilebilir" mikro etkileşimleri (`Micro-interactions`) ön planna tutar.

---

## 💎 Öne Çıkan Özellikler & Tasarım Kararları

- **Atmosferik & Moody UX:** Yapay zekanın tek tipleştirdiği parlak mor gradyanlı kart tasarımları yerine; derin sinematik siyah (`#0b0c10`), gümüş gri ve kadife kırmızısı (`#e50914`) kontrastı üzerine kurulu asimetrik arayüz.
- **Sinematik Zirve (Top 5 Visual Showcase):** Don Norman'ın "görsel odak" ilkesine uygun, en yüksek puanlı 5 filmi dinamik olarak ön plana çıkaran, üzerine gelindiğinde neon parlama efektiyle genişleyen unutulmaz vitrin öğesi.
- **Canlı TMDB API Entegrasyonu:** Veritabanını büyük resim linkleriyle kirletmeden, sadece film adına göre dünyanın en büyük film kütüphanesinden (The Movie Database) çalışma zamanında (`runtime`) orijinal afişleri otomatik olarak çözer.
- **Gelişmiş Filtreleme & Arama:** Gerçek zamanlı (Real-time) harf eşleşmeli arama (`ContainingIgnoreCase`) ve atmosferik kategori hapları (`Filter Pills`).

---

## 🛠️ Teknolojik Altyapı

### Backend
- **Java 17** & **Spring Boot**
- **Spring Data JPA** (Veritabanı soyutlama ve özel sorgu mekanizmaları)
- **H2 Database** (Bellek içi / In-memory veritabanı)
- **Spring Web** (RESTful API Mimarisi & CORS Yönetimi)

### Frontend
- **Semantic HTML5** & **CSS Custom Properties** (Değişkenler ile Tasarım Sistemi)
- **Vanilla JavaScript (Async/Await & Fetch API)** — Harici kütüphane veya JS Slop barındırmayan saf performans.
- **Google Fonts** (*Bricolage Grotesk* ve *Plus Jakarta Sans*)

---

## ⚙️ Uygulamanın Çalışma Mantığı (İşleyiş Mekanizması)

CineM projesi, gevşek bağlı (loosely coupled) kurumsal katmanlı mimari standartlarına göre çalışır. Bir veri akışının baştan sona işleyiş sırası şöyledir:

```text
[Tarayıcı / index.html] ──(Fetch API / JSON)──> [Controller] ──(DTO)──> [Service] ──(Entity)──> [Repository] ──> [H2 Veritabanı]
```

1. **İstek ve Arayüz Tetiklenmesi (Frontend):** Kullanıcı sayfayı açtığında veya formdan veri gönderdiğinde Vanilla JavaScript, asenkron `Fetch API` kullanarak Spring Boot backend uçlarına (`/api/movies`) bir HTTP isteği fırlatır.
2. **İsteğin Karşılanması (Controller Katmanı):** Dış dünyayı karşılayan `MovieController`, gelen HTTP isteklerini ve varsa URL parametrelerini yakalar. Güvenlik ve mimari standartlar gereği, ham veritabanı modellerini (`Entity`) dışarıya kapatmak için gelen veriyi `MovieRequestDTO` nesnesi olarak teslim alır ve iş mantığına aktarır.
3. **İş Mantığı ve Manuel Mapping (Service Katmanı):** Projenin kalbi olan `MovieService`, gelen DTO paketini el yordamıyla (Manuel Mapping) işleyerek doğrular ve bir `Movie` Entity modeline dönüştürür. Veritabanından gelen verileri dış dünyaya göndermeden önce de yine `convertToDto` fonksiyonu üzerinden `MovieResponseDTO` paketine dönüştürerek hassas veritabanı mimarisini gizler.
4. **Veritabanı Soyutlama (Repository Katmanı):** `MovieRepository` interface'i, `JpaRepository` sınıfından kalıtım alarak hiçbir SQL kodu yazmadan Hibernate aracılığıyla veritabanı işlemlerini yönetir. `findByCategory` ve `findByTitleContainingIgnoreCase` gibi özel metotlar, Spring Data'nın kelime analizi mekanizması sayesinde çalışma zamanında otomatik olarak optimize SQL sorgularına dönüştürülür.
5. **Kapanış ve API Cevabı:** Veritabanından dönen kayıtlar Service katmanında DTO listesine çevrilir, Controller katmanı bu listeyi `200 OK` durum koduyla birlikte JSON formatında tarayıcıya geri basar. Tarayıcıda bekleyen asenkron JavaScript akışı gelen JSON'ı okur ve TMDB API'sinden canlı afişleri de çözerek sinematik arayüze dinamik olarak işler.

---

## 📂 Katmanlı Mimari Yapısı

```text
src/main/java/cineM/
│
├── config/       # CORS kuralları ve MVC konfigürasyonları
├── controller/   # API uç noktaları (Endpoints)
├── dto/          # Veri transfer nesneleri (Request/Response ayrımı)
├── model/        # JPA Veritabanı Varlıkları (Entity)
├── repository/   # Spring Data JPA Arayüzleri (Custom Queries)
└── service/      # İş mantığı (Business Logic) ve Manuel Mapping
```

---

## 🚀 Projeyi Çalıştırma Adımları

Projenin yerel bilgisayarınızda (Local) ayağa kalkması için aşağıdaki adımları sırasıyla takip edin:

### 1. Ön Gereksinimler

Bilgisayarınızda **Java JDK 17 (veya üzeri)** ve bir IDE'nin (**Eclipse** veya **IntelliJ IDEA**) kurulu olduğundan emin olun.

### 2. Projeyi Klonlayın veya Açın

Git Bash üzerinden veya IDE yardımıyla projeyi açın:

```bash
cd ~/Desktop/JAVA
```

### 3. TMDB API Anahtarını Tanımlayın (Afişler İçin)

1. Projenin ön yüz dosyasını açın: `src/main/resources/static/index.html`
2. Dosyanın en altındaki `<script>` etiketinin içinde yer alan `TMDB_API_KEY` değişkenine kendi TMDB API anahtarınızı yapıştırın:

```javascript
   const TMDB_API_KEY = "BURAYA_KENDİ_API_KEYİNİZİ_YAPIŞTIRIN";
```

### 4. Uygulamayı Başlatın

* **IDE Üzerinden:** Ana paket altındaki Spring Boot başlangıç sınıfına sağ tıklayıp `Run As -> Spring Boot App` seçeneğini seçin.
* **Terminal Üzerinden (Maven):**

```bash
  ./mvnw spring-boot:run
```

### 5. Tarayıcıdan Erişin

Uygulama başarıyla başlatıldıktan sonra tarayıcınızı açın ve şu adrese gidin:

```text
http://localhost:8080/
```

---

## 🔌 API Uç Noktaları (Endpoints)

Proje, Postman veya ön yüz üzerinden test edilebilecek şu REST uç noktalarını dışarıya sunar:

| Metot | API Yolu | Açıklama |
| --- | --- | --- |
| **GET** | `/api/movies` | Tüm film arşivini listeler. |
| **GET** | `/api/movies/{id}` | ID'ye göre tek bir film getirir. |
| **POST** | `/api/movies` | Arşive yeni bir atmosferik film ekler. |
| **PUT** | `/api/movies/{id}` | Mevcut filmi günceller. |
| **DELETE** | `/api/movies/{id}` | Filmi arşivden kalıcı olarak siler. |
| **GET** | `/api/movies/top-rated` | En yüksek puanlı 5 filmi getirir (Vitrin için). |
| **GET** | `/api/movies/category/{category}` | Belirli bir atmosfere/kategoriye göre filtreler. |
| **GET** | `/api/movies/search?title={query}` | Film adına göre harf eşleştirerek arama yapar. |

---

*Mustafa Karakoyun — 2026*