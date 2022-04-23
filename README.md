# KARTACA-UrunveServisGelistirme

#### SpringBoot-Eureka Server-PostgreSql-Docker-ReactJs

### Anahtar kodum:
iCCCCCDkNbqK_qEVuxCI78vGegzPZu2tjug6N8kQhrWy6QjDll6MkpQ1o2wbYDpsE4DMbLBO7KjnJ0ZJOJTTGBj_LyTM76IlNQy72jhiV6oGXfdBWQdjpoi-cBjTH55BgpkBZoDU7lB2ONx2QvRVX6FX6pq6T5Gy7gTaUwwRJi4khMBA48gXIyrda1MtypNmlfmUxzOd96OnjkgTrwq4J96cdRSuYH1st8xyQ_3ZQqr_LtPmo0uUcsK=

### UYGULAMA
### Servis
#### Service Registry: http://localhost:8762 adresinden register olan servislerimizi gorebiliriz.
#### Account Service:  8086 portuna gateway servisinden yonlendirme yapilir. Userlar register ve login islemlerini gerceklestirebilir.
     Userlar login olduktan sonra session bilgileri rediste tutuluyor.
   ##### Redisi dockerda calistirmak icin asagidaki islemler gerceklestirilir:
     
   ```
     docker run --name my-redis -p 6379:6379 -d redis
 
     
   ```
     docker exec -it my-redis sh
  
     
   ```
     redis-cli
   
     
   ```
     monitor
              
#### Calendar Service:  8087 portuna gateway servisinden yonlendirme yapilir. Calendar olusumu, calendara event ekleme, silme,getirme islemleri yapilir.
#### Gateway:  http://localhost:9191 adresinden diger servislere (account-service, calendar-service) yonlendirme yapar. 
      Account service:
         http://localhost:9191/api/user
         http://localhost:9191/api/register
         
      Calendar service:
         http://localhost:9191/calendars/addition
         http://localhost:9191/calendars//deletion/{calendarId}
         http://localhost:9191/calendars//{calendarId}
         http://localhost:9191/calendars/events/add
         http://localhost:9191/calendars/events/delete/{calendarEventId}
      
#### RestTemplate 
      Servisler arasi senkron haberlesme icin resttemplate yapisini kullandim.          
### Docker
      Servislerin Dockerfile'larini olusturdum. 
      Gateway klasorunun altinda docker-compose.yml dosyasi mevcuttur. 
      Ancak hepsini tamamlayamadim.
      
### Frontend
    React js ile yazdim. Frontend de istenilenlerin hepsini tamamlayamadim.
           http://localhost:3000/register
           http://localhost:3000/login
           http://localhost:3000/mycalendar
