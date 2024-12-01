# Yetki Kontrol Mekanizması (sedefciğim unutmasın diye not )

Bu proje, kullanıcı yetkilerini JWT üzerinden kontrol etmek için Spring Security kullanmaktadır. Aşağıda bu sürecin nasıl işlediği adım adım açıklanmıştır:

### Süreç Açıklaması

1. `@PreAuthorize("hasAnyAuthority('READ')")` ifadesi, kullanıcının JWT'sinde yer alan yetkileri (**authorities**) kontrol eder.
2. Kullanıcının yetkileri, `generateClaims` metodu ile JWT'ye eklenir.
3. `getAuthentication` metodu, JWT'yi doğrular ve içindeki yetkileri çözümler.
4. Bu yetkiler, `SimpleGrantedAuthority` nesneleri olarak işlenir ve Spring Security tarafından kullanılır.
5. Eğer kullanıcının yetkileri arasında `READ` bulunuyorsa, ilgili metoda erişim sağlanır. Aksi halde `403 Forbidden` hatası döner.
