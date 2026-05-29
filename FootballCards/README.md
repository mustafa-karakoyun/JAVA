# ⚽ Football Card Data Engine (FCManager)

Spring Boot JPA ve REST API mimarisi üzerine inşa edilmiş, endüstriyel düzeyde bir futbolcu kartı yönetim ve analitik motorudur. Uygulama; oyuncu reyting analizi, gelişmiş filtreleme mekanizmaları ve otomatik veri besleme (data seeding) özelliklerini siber-endüstriyel, karanlık mod odaklı modern bir mimari ile dış dünyaya sunar.

---

## 🛠️ Teknolojik Altyapı & Mimari

Proje, katmanlı mimari (Layered Architecture) prensiplerine sadık kalınarak geliştirilmiştir:

- **Backend:** Java 17, Spring Boot, Spring Data JPA, Jakarta Validation
- **Veritabanı:** H2 Database / PostgreSQL (Uygulama ayağa kalkarken otomatik tablo üretimi)
- **Veri Transferi:** DTO (Data Transfer Object) Deseni (`Request` ve `Response` ayrımı)

---

## 📁 Proje Klasör Hiyerarşisi

```text
src/main/java/cards/
│
├── config/
│   └── CardDataLoader.java          # Uygulama başında otomatik test verisi yükleyici
├── controller/
│   └── CardController.java          # REST API uç noktaları (Endpoints)
├── dto/
│   ├── Request.java                 # Jakarta doğrulama kuralları içeren girdi DTO'su
│   └── Response.java                # Güvenli dış dünyaya veri açma DTO'su
├── model/
│   └── Card.java                    # Veritabanı Entity katmanı (@Table, @Id)
├── repository/
│   └── cardsRepository.java          # Özel sorguları barındıran veri katmanı (Query Methods)
└── Application.java                 # Spring Boot başlangıç sınıfı
```

---

## 🚀 API Uç Noktaları (Endpoints)

Sistem `/api/v1/cards` kök dizini üzerinden aşağıdaki RESTful standartlarına uygun istekleri kabul eder:

### Temel CRUD İşlemleri


| Metot | Endpoint | Açıklama | HTTP Durum Kodu |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/v1/cards` | Sistemdeki tüm futbolcu kartlarını listeler | `200 OK` |
| **GET** | `/api/v1/cards/{id}` | Belirtilen ID'ye sahip kartı getirir | `200 OK` / `404 Not Found` |
| **POST** | `/api/v1/cards` | Yeni bir kart oluşturur (Validation korumalı) | `201 Created` / `400 Bad Request` |
| **PUT** | `/api/v1/cards/{id}` | Mevcut bir kartın bilgilerini günceller | `200 OK` / `404 Not Found` |
| **DELETE** | `/api/v1/cards/{id}` | Belirtilen kartı sistemden kalıcı olarak siler | `204 No Content` / `404 Not Found` |

### Gelişmiş Filtreleme & Analitik Motoru


| Metot | Endpoint | Açıklama |
| :--- | :--- | :--- |
| **GET** | `/api/v1/cards/club/{clubName}` | Belirli bir kulübün oyuncularını listeler (Case-Insensitive) |
| **GET** | `/api/v1/cards/nation/{nationName}` | Belirli bir ülkenin oyuncularını listeler (Case-Insensitive) |
| **GET** | `/api/v1/cards/top?min=85` | Belirtilen reyting sınırının üzerindeki yıldızları getirir |
| **GET** | `/api/v1/cards/search?name=...` | Oyuncu isimlerinde akıllı canlı arama motoru (LIKE) |
| **GET** | `/api/v1/cards/club/{clubName}/average-age` | Kulübün yaş ortalamasını dönen özel analitik servis |

---

## 🧪 Veri Doğrulama (Validation) Kuralları

Sisteme hatalı veya kirli veri girişini engellemek amacıyla `Request DTO` katmanında şu sınırlandırmalar aktif edilmiştir:

* **`fullName`**: Boş geçilemez (`@NotBlank`).
* **`age`**: Sadece pozitif değer alabilir (`@Positive`).
* **`overall`**: Futbolcu kartı standardı gereği minimum `0`, maksimum `100` değerleri arasında olmalıdır (`@Min`, `@Max`).
* **`nation`**: Boş geçilemez (`@NotBlank`).
* **`club`**: Boş geçilemez (`@NotBlank`).

---

## 🏃‍♂️ Kurulum ve Çalıştırma

1. Projenin bilgisayarınızda derlenebilmesi için **Java 17** kurulu olduğundan emin olun.
2. Projeyi Eclipse veya IntelliJ IDE'nize **Existing Maven Project** olarak içe aktarın (Import).
3. `pom.xml` bağımlılıklarının indirilmesi için projeye sağ tıklayıp `Maven -> Update Project` (Force Update) deyin.
4. Ana başlangıç sınıfını bulun ve `Run As -> Spring Boot App` komutuyla projeyi başlatın.
5. Tarayıcınızdan `http://localhost:8080/api/v1/cards` adresine giderek H2 veritabanı paneline erişebilirsiniz.
