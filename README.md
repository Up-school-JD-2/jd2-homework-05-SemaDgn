## Ödeme işlemleri uygulaması

Kullanıcıdan kredi kartı bilgilerini kullanarak ödeme yapmasına izin veren bir uygulama yapınız. Kullanıcı ödeme yaparken ödeme tutarını, kart numarasını, son kullanma tarihini ve güvenlik kodunu girmelidir. Kullanıcıdan bilgiler alındıktan sonra bir doğrulama işlemi yapılmalıdır, kısıtlamalar aşağıdadır (Kısıtlamalar için özel exception sınıfları oluşturun, örn. InvalidAmountException, InvalidCardNumberException ). Kısıtlamaları geçtikten sonra **pay** isimli bir metod çağırılmalıdır. Bu metod içerisinde 0-100 arasında bir random sayı üretin, eğer sayı 75'den yüksek ise kendinizin oluşturacağı ***SystemNotWorkingException*** fırlatın ve işlemi bir kez daha deneyin.

- Ödeme tutarının formatı doğru olmalıdır, negatif bir değer veya virgüllü bir değer girilemez.
- Kullanıcının girdiği kart numarası doğru olmalıdır (16 haneli, rakamlardan oluşan)
- Son kullanma tarihi (12/2013) gibi geçmiş bir yıl yada 13 gibi bir ay girilemez.
- Güvenlik kodu formatı doğru olmalı (3 karakter, rakamlardan oluşan)
- Ödeme işlemi sırasında bir hata oluştuğunda özel olarak oluşturacağınız SystemNotWorkingException fırlatılmalı, ve 1 defa tekrar işlem denenmeli.

Uygulamanın akışında, kısıtlamalar uygulandığı sürece serbestsiniz. 

