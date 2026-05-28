# 🛒 Spring Boot & UX Optimized Product Management System

Bu proje, bir işletmenin stok maddelerini (ürünlerini) yönetebilmesi için geliştirilmiş, kurumsal standartlara uygun **Katmanlı Mimari (Layered Architecture)** kullanan bir REST API ve bu API ile haberleşen **UX odaklı bir yönetim paneli (Frontend)** uygulamasıdır.

Proje, final sınavı hazırlığı kapsamında backend motorundan arayüz entegrasyonuna kadar tüm CRUD döngüsünü pratik etmek amacıyla geliştirilmiştir.

---

## 🚀 Kullanılan Teknolojiler

### Backend (Arka Plan)
* **Java 17** & **Spring Boot 3.x**
* **Spring Data JPA:** Veritabanı nesne-ilişki modellemesi (ORM) için.
* **Spring Web:** RESTful API uç noktaları (Endpoints) sağlamak için.
* **Jakarta Validation:** Giriş verilerinin güvenliğini ve doğruluğunu sağlamak için.
* **H2 Database:** Hafıza içi (In-Memory), hızlı test edilebilir veritabanı.

### Frontend (Ön Yüz)
* **HTML5 & Modern CSS3** (Özel grid ve gölgelendirmeler)
* **Bootstrap 5:** Mobil uyumlu ve temiz bileşen tasarımları için.
* **FontAwesome:** Süreç odaklı görsel ikonlar için.
* **Vanilla JavaScript (Fetch API):** Sayfa yenilenmeden arka planla asenkron veri haberleşmesi için.

---

## 🏗️ Mimari Yapı (Layered Architecture)

Proje, sorumlulukların ayrılması (Separation of Concerns) ilkesine dayanarak katmanlı mimari ile inşa edilmiştir:

1. **Entity (`Product.java`):** Veritabanındaki `PRODUCT` tablosunun Java'daki karşılığı.
2. **Repository (`ProductRepository.html`):** Veritabanı sorgularını otomatik yöneten veri erişim katmanı.
3. **Service (`ProductService.java`):** İş mantığının (Business Logic) ve veri akışının yönetildiği merkez katman.
4. **Controller (`ProductController.java`):** Dış dünyadan gelen HTTP isteklerini karşılayan REST API katmanı.
5. **DTO & Mapper:** Veritabanı modellerini dış dünyadan soyutlayan güvenli veri transfer yapıları.

---

## 🔒 Güvenlik & Veri Doğrulama (Validation)

Sisteme hatalı veya kötü niyetli veri sızmasını engellemek adına `ProductRequestDTO` üzerinde katı kurallar uygulanmıştır:
* `name` alanı boş veya sadece boşluklardan oluşamaz (`@NotBlank`).
* `price` alanı her zaman 0'dan büyük bir değer olmak zorundadır (`@Positive`).

Kurallara uymayan istekler daha servis katmanına ulaşmadan API tarafından **`400 Bad Request`** kodu ile reddedilir.

---

## 🎨 Ön Yüz Kullanıcı Deneyimi (UX) Özellikleri

Don Norman'ın tasarım ilkeleri dikkate alınarak arayüz üzerinde şu UX geliştirmeleri yapılmıştır:
* **Hata Önleme (Error Prevention):** Kritik silme işlemlerinde kullanıcının yanlışlıkla veriyi kaybetmesini önlemek amacıyla tarayıcı uyarısı yerine uygulama içi modern onay kutuları (**Modal**) kullanılmıştır.
* **Anında Geri Bildirim (Feedback):** Ürün ekleme, silme veya bağlantı hatası durumlarında ekranın sağ üst köşesinde renk kodlu (Yeşil: Başarı, Kırmızı: Hata) canlı bildirimler (**Toasts**) akmaktadır.
* **Boş Durum Grafiği (Empty State):** Stokta hiç ürün kalmadığında kullanıcının sistemin boş olduğunu anlamasını sağlayan yönlendirici bir grafiksel alan tasarlanmıştır.

---

## ⚙️ Projeyi Yerelde Çalıştırma

1. Projeyi bilgisayarınıza klonlayın.
2. Eclipse veya IntelliJ IDE'niz üzerinden `demo` projesini bir Maven projesi olarak içe aktarın (Import).
3. `com.mustafa.demo.Application.java` sınıfına sağ tıklayıp **Run As -> Java Application** diyerek projeyi başlatın.
4. Tarayıcınızı açın ve şu adrese gidin:
   ```text
   http://localhost:8080/index.html
