RequestManager

Aplikacja ma za zadanie zmianê stanu wniosku w zale¿noœci od przechodzenia pomiêdzy kolejnymi etapami weryfikacji.
Projekt zosta³ napisany w œrodowisku Java (jdk1.7.0_51) i Netbeans 8.0.2. Projekt. Narzêdziem wspomagaj¹cym jest Apache Maven.

W tym celu zosta³o utworzne 4 pakiety:

com.requestmanager.main - main class
com.requestmanager.controller - business logic
com.requestmanager.model - model
com.requestmanager.utils - helper

RequestManager.java - inicjalizacja aplikacji. Utworzono 5 scenariuszy, które tworz¹ podstawowy flow zmiany stanów wniosków.
RequestHandler.java - klasa w której zosta³y zaimplementowane wszystkie metody do obs³ugi zmianów stanu w aplikacji.

showRequests(itemsPerPage) - wyœwietla wszystkie wnioski z odpowieni¹ iloœci¹ na stronie
showFilteredRequests(String requestName, EnumStatus.Status status, int itemsPerPage) - wyœwitla przefiltrowanie wnioski z odpowiedni¹ iloœci¹ na stronie
createNewRequest(String requestName, String requestDescription) - utworzenie nowego wniosku
createNewVersion(Request r, EnumStatus.Status status) - tworzy historiê zmiany stanu danego wniosku
deleteRequest(String toBeDeleted, String requestReasonRejected, EnumStatus.Status s) - zmienia stan wniosku na Deleted
verifyRequest(String toBeVerified, String requestDescription, EnumStatus.Status s) - zmienia stan wniosku na verified
rejectRequest(String toBeRejected, String requestReasonRejected, EnumStatus.Status s) - zmienia stan wniosku na rejected
acceptRequest(String toBeAccepted, EnumStatus.Status s) - zmienia stan wniosku na accepted
publishRequest(String toBePublished, EnumStatus.Status s) - zmienia stan wniosku na published


Wszystkie zmiany s¹ zapisywane w pliku requests.xml w g³ownym katalogu aplikacji. Struktura drzewka umo¿liwia utworzenie relacji pomiêdzy histori¹ a wnioskiem.
W celu wczytywania i zapisywania powy¿szej struktury u¿yto bibliotek JAXB.

Model danych zawiera nastêpuj¹ce definicje:
Version.java - obiekt przechowuj¹cy historiê zmian wniosku
Requests.java - obiekt przechowuj¹cy listê wniosków
Request.java - obiekt przechowuj¹cy wniosek
EnumStatus.java - mo¿liwe stany wniosku

W pakiecie Utils znajduj¹ siê funkcje pomocnicze.