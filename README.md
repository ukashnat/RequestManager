RequestManager

Aplikacja ma za zadanie zmian� stanu wniosku w zale�no�ci od przechodzenia pomi�dzy kolejnymi etapami weryfikacji.
Projekt zosta� napisany w �rodowisku Java (jdk1.7.0_51) i Netbeans 8.0.2. Projekt. Narz�dziem wspomagaj�cym jest Apache Maven.

W tym celu zosta�o utworzne 4 pakiety:

com.requestmanager.main - main class
com.requestmanager.controller - business logic
com.requestmanager.model - model
com.requestmanager.utils - helper

RequestManager.java - inicjalizacja aplikacji. Utworzono 5 scenariuszy, kt�re tworz� podstawowy flow zmiany stan�w wniosk�w.
RequestHandler.java - klasa w kt�rej zosta�y zaimplementowane wszystkie metody do obs�ugi zmian�w stanu w aplikacji.

showRequests(itemsPerPage) - wy�wietla wszystkie wnioski z odpowieni� ilo�ci� na stronie
showFilteredRequests(String requestName, EnumStatus.Status status, int itemsPerPage) - wy�witla przefiltrowanie wnioski z odpowiedni� ilo�ci� na stronie
createNewRequest(String requestName, String requestDescription) - utworzenie nowego wniosku
createNewVersion(Request r, EnumStatus.Status status) - tworzy histori� zmiany stanu danego wniosku
deleteRequest(String toBeDeleted, String requestReasonRejected, EnumStatus.Status s) - zmienia stan wniosku na Deleted
verifyRequest(String toBeVerified, String requestDescription, EnumStatus.Status s) - zmienia stan wniosku na verified
rejectRequest(String toBeRejected, String requestReasonRejected, EnumStatus.Status s) - zmienia stan wniosku na rejected
acceptRequest(String toBeAccepted, EnumStatus.Status s) - zmienia stan wniosku na accepted
publishRequest(String toBePublished, EnumStatus.Status s) - zmienia stan wniosku na published


Wszystkie zmiany s� zapisywane w pliku requests.xml w g�ownym katalogu aplikacji. Struktura drzewka umo�liwia utworzenie relacji pomi�dzy histori� a wnioskiem.
W celu wczytywania i zapisywania powy�szej struktury u�yto bibliotek JAXB.

Model danych zawiera nast�puj�ce definicje:
Version.java - obiekt przechowuj�cy histori� zmian wniosku
Requests.java - obiekt przechowuj�cy list� wniosk�w
Request.java - obiekt przechowuj�cy wniosek
EnumStatus.java - mo�liwe stany wniosku

W pakiecie Utils znajduj� si� funkcje pomocnicze.