# Sterownik rozmyty

1. Cel ćwiczenie

Celem ćwiczenia było stworzenie sterownika rozmytego z wykorzystaniem JFuzzyLogic.

2. Opis sterownika

Stworzyłem sterownik, który steruje systemem podlewania roślin. Posiada on 4 wejścia i 1 wyjście.

Wejścia to :
* Wymagania rośliny - wartość od 0 do 10 mówiąca o typie rośliny, im większa wartość, tym
więcej wody potrzebuje.
* Wilgotność gleby - aktualna wilgotność gleby wyrażona w %.
* Temperatura - aktualna temperatura powietrza, zakres temperatury przyjąłem od 0 do 45
stopni. Jest to główny czynnik odpowiedzialny za wysychanie gleby.
* Nasłonecznienie - moc naświetlenia gleby przez słońce wyrażona w %.

Wyjście to poziom na, którym mają działać zraszacze. Wartość wyjściowa jest z przedziału 0 do 10
i jest ona przerabiana na 5 możliwych poziomów zraszania (0, 1, 2, 3 lub 4), gdzie 0 to brak
nawodnienia, a 4 to najsilniejsze nawodnienie.

Do wyznaczenia odpowiedniej wartości nawodnienia korzystam z COG (środek ciężkości).

Kolejne bloki reguł odpowiadają za kolejne typy roślin. Jest w sumie 5 typów roślin i dla każdego z nich
powstał jeden blok reguł, który zawiera około 25 reguł. Reguły powstawały poprzez przejście przez
różne poziomy wilgotności gleby (oczywistym jest, że jeśli jest sucho to nieważne jaka pogoda to i tak
trzeba podlać i odwrotnie gdy jest mokro nie wolno podlewać). Dla każdego poziomu starałem się
przejść przez możliwe kombinacje temperatury i nasłonecznienia. Reguły dla skrajnych wartości
wilgotności gleby były grupowane. W bloku przechodzę od najniższej wilgotności do najwyższej, od
zimna do gorąca i od braku słońca do pełnego nasłonecznienia. Jako przykład podam blok reguł dla
rośliny lubiącej mało wody.

3. Sposób uruchomienia

Program możemy uruchomić w jednym z dwóch trybów. Pierwszy jest podobny do trybu z zajęć
i wymaga podania jako pierwszy argument wartości ‘test’. Następnie podajemy wymagania rośliny,
wilgotność gleby, temperaturę i nasłonecznienie. W efekcie otrzymujemy zestaw wykresów
i wyliczoną wartość wyjścia. Drugim trybem jest symulacja i wymaga podania jako pierwsze słowa
‘simulation’, następnie podajemy ilość kroków symulacji oraz wymagania rośliny pierwszej i drugiej.

4. Symulacja

Symulacja jest przeprowadzana dla dwóch typów roślin. Możemy sobie to wyobrazić jak dwie grządki
dwóch typów roślin leżące obok siebie. Współdzielą one pogodę, jednakże mają swoją wilgotność
gleby i potrzebny poziom nawodnienia. Symulacja polega wykonaniu ciągu instrukcji tyle razy ile
podano w argumencie programu. Ciąg ten składa się z następujących instrukcji:

* Zmiany pogody - następuje zmiana temperatury i nasłonecznienia o losową wartość.
Temperatura może zmienić się maksymalnie o 8 stopni, nasłonecznienie natomiast o 15%.
Może również pojawić się deszcz, który może być słaby lub silny. Prawdopodobieństwo
wystąpienia deszczu zostało ustawione na 5%.
* Wysuszenie gleby - zmiana wilgotności gleby zgodnie z panującymi warunkami
atmosferycznymi.
* Obliczenie nawodnienia - wywołanie sterownika rozmytego do obliczenia potrzebnego
nawodnienia.
* Nawadnianie - zwiększenie wilgotności gleby zgodnie z poziomem nawadniania.
* Wypisanie aktualnego stanu.

Zdecydowałem się na symulowanie dwóch typów roślin na raz, aby pokazać wpływ wymagań na
zachowanie się sterownika i poziom wilgotności gleby pomimo identycznych warunków pogodowych.

Na koniec symulacji tworzę trzy wykresy i zapisuje je do plików. Jeden prezentuje zmiany pogody. Dwa
kolejne pokazują dla każdej z gleb poziom wilgotności oraz poziom nawadniania.
