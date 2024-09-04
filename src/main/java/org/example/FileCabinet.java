package org.example;

import java.util.*;
/*
    Celem zadania było zaimplementowanie metod z jak najmniejszą ilością powtarzającego się kodu, dlatego została
    utworzona metoda flattenAll, która zwraca listę wszystkich folderów znajdujących się w strukturze. Dzięki temu
    implementacja właściwych metod zawiera niewiele kodu. Do zadania zostały napisane testy wraz z testowymi
    implementacjami oraz przykładowymi strukturami wykorzystywanymi do testów.
 */

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders;
    }

    /*
        Metoda na wstępie sprawdza poprawność dostarczonego imienia (korzystając ze specjalnie przygotowanej funkcji).
        Używam tu trójargumentowego operatora logicznego, który, jeśli name jest poprawne, przechodzi do utworzenia listy
        zawierającej wszystkie foldery w strukturze. Następnie tworzony jest strumień (stream), który kolejno filtruje,
        czy nazwa folderu jest tą, której szukamy. Jeśli tak, metoda zwraca dany folder jako Optional; w przeciwnym razie
        zwraca pusty Optional. W przypadku, gdy name jest niepoprawne, metoda również zwraca pusty Optional.
     */

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return validateFolderName(name) ?
                flattenAll().stream()
                        .filter(folder -> checkName(folder, name))
                        .findFirst()
                : Optional.empty();
    }

    /*
        Podobnie jak poprzednio, z tym że sprawdzany jest rozmiar. Główna różnica polega na tym, że metoda nie szuka
        pierwszego elementu, lecz wszystkich elementów spełniających warunek i dodaje je do listy. Natomiast, jeśli
        rozmiar jest niepoprawny, zwracana jest pusta lista.
     */

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return validateSize(size) ?
                flattenAll().stream()
                        .filter(folder -> checkSize(folder, size))
                        .toList()
                : Collections.emptyList();
    }

    /*
        Metoda w tej strukturze zamienia każdy element na 1 i wykonuje ich sumowanie, co daje nam łączną liczbę
        wszystkich folderów w strukturze. Metodę tę można by było przedstawić inaczej, używając funkcji count(),
        która zlicza wszystkie elementy, ale wówczas zwraca wartość typu long, co wymagałoby rzutowania tej wartości
        na typ int.
     */

    @Override
    public int count() {
        return flattenAll().stream()
                .mapToInt(folder -> 1)
                .sum();
    }

    /*
        Metoda tworzy pełną strukturę folderów. Jeśli lista folderów nie jest pusta, tworzony jest strumień (stream) ze
        wszystkich folderów. Wewnątrz flatMap wywoływana jest funkcja, która zwraca wszystkie zagnieżdżone foldery jako
        listę dla każdego folderu, a następnie konwertuje tę listę na strumień. Wszystkie elementy są następnie dodawane
        do jednej listy. Jeśli lista folderów jest pusta lub równa null, metoda zwraca pustą listę.
     */

    private List<Folder> flattenAll() {
        return (folders != null) ?
                folders.stream()
                        .flatMap(folder -> flattenFolder(folder).stream())
                        .toList()
                : Collections.emptyList();
    }

    /*
        Metoda tworzy strukturę dla poszczególnych folderów z listy folders, która jest przekazywana jako argument.
        W ramach metody sprawdzane jest, czy dany folder jest zwykłym folderem, czy MultiFolderem. Jeśli jest to
        zwykły folder, metoda zwraca listę zawierającą tylko ten jeden folder. W przeciwnym razie, jeśli folder jest
        MultiFolderem, metoda rekurencyjnie przetwarza każdy z zawartych w nim folderów, wywołując tę samą metodę.
        Wszystkie wyniki są następnie łączone w jedną listę. W ten sposób tworzona jest pełna, spłaszczona struktura
        zawierająca wszystkie foldery z głównej listy.
     */

    private List<Folder> flattenFolder(Folder folder) {
        List<Folder> folderList = new ArrayList<>(Collections.singletonList(folder));
        folderList.addAll(folder instanceof MultiFolder multiFolder ?
                multiFolder.getFolders().stream()
                        .flatMap(f -> flattenFolder(f).stream())
                        .toList()
                : Collections.emptyList());
        return folderList;
    }

    /*
        Prosta metoda sprawdzająca poprawność podanej nazwy folderu, która zwraca true, jeśli nazwa jest poprawna.
        Metoda sprawdza, czy wartość name nie jest null oraz czy nie składa się wyłącznie z białych znaków.
     */

    private boolean validateFolderName(String name) {
        return name != null && !name.isBlank();
    }

    /*
        Prosta metoda porównująca folder name z dostarczonym name, zwracająca true, gdy są identyczne.
     */

    private boolean checkName(Folder folder, String name) {
        return folder.getName().equals(name);
    }

    /*
        Prosta metoda sprawdzająca poprawność podawanego rozmiaru folderu, która zwraca true, jeśli nazwa jest poprawna.
        Sprawdza czy size nie jest nullem, a także czy jego nazwa to Small, medium oraz large, specjalnie do tego został
        utworzony enum zawierający te stałe oraz posiadający metodę porównawczą.
     */

    private boolean validateSize(String size) {
        return size != null && Size.isSizeExist(size);
    }

    /*
        Prosta metoda porównująca folder size z dostarczonym size, zwracająca true, gdy są identyczne.
     */

    private boolean checkSize(Folder folder, String size) {
        return folder.getSize().equals(size);
    }
}
