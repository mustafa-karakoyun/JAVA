-- 1. Test kullanıcısını ekliyoruz (ID: 1)
INSERT INTO users (id, name, email) VALUES (1, 'Mustafa', 'mustafa@finance.com');

-- 2. Test kullanıcısına bağlı bir takip listesi oluşturuyoruz (ID: 1)
INSERT INTO watch_list (id, name, user_id) VALUES (1, 'Teknoloji Hisselerim', 1);