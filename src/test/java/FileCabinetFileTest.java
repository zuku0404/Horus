import examples.ExampleTest;
import org.example.FileCabinet;
import org.example.Folder;
import org.example.Size;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class FileCabinetFileTest {
    FileCabinet fileCabinet;


    /// Test for findFolderByName method
    @Nested
    @DisplayName("Tests for find folder by name method")
    class FindFolderByNameTest {
        @Test
        @DisplayName("Find folder by name in not initialized list")
        void findFolderByNameInNotInitializedList() {
            fileCabinet = new FileCabinet(null);

            Optional<Folder> result = fileCabinet.findFolderByName("folder1");
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder by name in empty list")
        void findFolderByNameInEmptyList() {
            fileCabinet = new FileCabinet(ExampleTest.EMPTY_FOLDER_LIST);

            Optional<Folder> result = fileCabinet.findFolderByName("folder1");
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder with null name")
        void findFolderWithNullName() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_FOLDER_LIST);

            Optional<Folder> result = fileCabinet.findFolderByName(null);
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder with empty string as name")
        void findFolderWithEmptyName() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_FOLDER_LIST);

            Optional<Folder> result = fileCabinet.findFolderByName("");
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder by name in list with one folder")
        void findFolderByNameInListWithOneFolder() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_FOLDER_LIST);

            Optional<Folder> result = fileCabinet.findFolderByName("folder1");
            Assertions.assertTrue(result.isPresent());
        }

        @Test
        @DisplayName("Find folder by name in list with one MultiFolder")
        void findFolderByNameInListWithOneMultiFolder() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_MULTIFOLDER_LIST);

            Optional<Folder> result = fileCabinet.findFolderByName("folder1");
            Assertions.assertTrue(result.isPresent());
        }

        @Test
        @DisplayName("Find non-existent folder name in list")
        void findNonExistentFolderName() {
            fileCabinet = new FileCabinet(ExampleTest.FOLDER_LIST_WITH_ALL_SIZES);

            Optional<Folder> result = fileCabinet.findFolderByName("folder4");
            Assertions.assertTrue(result.isEmpty());

            result = fileCabinet.findFolderByName("folder5");
            Assertions.assertTrue(result.isEmpty());
        }


        @Test
        @DisplayName("Find existing folder names in list with only folders")
        void findFolderNameInListOfFolders() {
            fileCabinet = new FileCabinet(ExampleTest.FOLDER_LIST_WITH_ALL_SIZES);

            Optional<Folder> result = fileCabinet.findFolderByName("folder1");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder2");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder3");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder4");
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder name in list with folders and non-nested MultiFolders")
        void findFolderNameInListWithNonNestedMultiFolders() {

            fileCabinet = new FileCabinet(ExampleTest.NON_NESTED_MULTIFOLDER_LIST);

            Optional<Folder> result = fileCabinet.findFolderByName("folder1");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder2");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder3");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder4");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder5");
            Assertions.assertTrue(result.isPresent());
        }

        @Test
        @DisplayName("Find folder name in list with folders and first-level nested MultiFolders")
        void findFolderNameInListWithFirstLevelNestedMultiFolders() {
            fileCabinet = new FileCabinet(ExampleTest.FIRST_LEVEL_NESTED_MULTIFOLDER_LIST);

            Optional<Folder> result = fileCabinet.findFolderByName("folder1");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder2");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder3");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder4");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder5");
            Assertions.assertTrue(result.isPresent());
        }

        @Test
        @DisplayName("Find folder name in deeply nested MultiFolders")
        void findFolderNameInDeeplyNestedMultiFolders() {
            fileCabinet = new FileCabinet(ExampleTest.DEEPLY_NESTED_MULTIFOLDER_LIST);

            Optional<Folder> result = fileCabinet.findFolderByName("folder4");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder3");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder2");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder1");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder5");
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder name in MultiFolder with mixed nesting levels")
        void findFolderNameInMixedNestingLevels() {
            fileCabinet = new FileCabinet(ExampleTest.MIXED_NESTING_LEVEL_MULTIFOLDER_LIST);

            Optional<Folder> result = fileCabinet.findFolderByName("folder7");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder6");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder4");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder1");
            Assertions.assertTrue(result.isPresent());

            result = fileCabinet.findFolderByName("folder8");
            Assertions.assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("Tests for find folders by size")
    class FindFoldersBySizeTest {

        @Test
        @DisplayName("Find folder by size in not Initialized list")
        void findFolderBySizeInNotInitializedList() {
            fileCabinet = new FileCabinet(null);

            List<Folder> result = fileCabinet.findFoldersBySize(Size.LARGE.name());
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder by size which is null in list")
        void findFolderByNullSizeInList() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_FOLDER_LIST);

            List<Folder> result = fileCabinet.findFoldersBySize(null);
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder by size which is incorrect in list")
        void findFolderByEmptySizeInList() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_FOLDER_LIST);

            List<Folder> result = fileCabinet.findFoldersBySize("XXL");
            Assertions.assertTrue(result.isEmpty());

        }

        @Test
        @DisplayName("Find folder by size in an empty list")
        void findFolderBySizeInEmptyList() {
            fileCabinet = new FileCabinet(ExampleTest.EMPTY_FOLDER_LIST);

            List<Folder> result = fileCabinet.findFoldersBySize("folder1");
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder by size in a list with one folder")
        void findFolderBySizeInListWithOneFolder() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_FOLDER_LIST);

            List<Folder> result = fileCabinet.findFoldersBySize(Size.LARGE.name());
            Assertions.assertEquals(1, result.size());

            result = fileCabinet.findFoldersBySize(Size.MEDIUM.name());
            Assertions.assertTrue(result.isEmpty());

            result = fileCabinet.findFoldersBySize(Size.SMALL.name());
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find folder by size in a list with one MultiFolder")
        void findFolderBySizeInListWithOneMultiFolder() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_MULTIFOLDER_LIST);

            List<Folder> result = fileCabinet.findFoldersBySize(Size.LARGE.name());
            Assertions.assertEquals(1, result.size());

            result = fileCabinet.findFoldersBySize(Size.MEDIUM.name());
            Assertions.assertTrue(result.isEmpty());

            result = fileCabinet.findFoldersBySize(Size.SMALL.name());
            Assertions.assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Find  folder size with different configuration with only folders list")
        void findFolderBySizeWithDifferentConfigurationOnlyFolderList() {
            fileCabinet = new FileCabinet(ExampleTest.FOLDER_LIST_WITH_ALL_SIZES);


            List<Folder> result = fileCabinet.findFoldersBySize(Size.LARGE.name());
            Assertions.assertEquals(1, result.size());

            result = fileCabinet.findFoldersBySize(Size.MEDIUM.name());
            Assertions.assertEquals(1, result.size());

            result = fileCabinet.findFoldersBySize(Size.SMALL.name());
            Assertions.assertEquals(1, result.size());
        }

        @Test
        @DisplayName("Find  folder size with different configuration with only folders list without large size")
        void findFolderBySizeWithDifferentConfigurationOnlyFolderListWithoutLargeSize() {
            fileCabinet = new FileCabinet(ExampleTest.FOLDER_LIST_WITHOUT_LARGE_SIZE);

            List<Folder> result = fileCabinet.findFoldersBySize(Size.LARGE.name());
            Assertions.assertTrue(result.isEmpty());

            result = fileCabinet.findFoldersBySize(Size.MEDIUM.name());
            Assertions.assertEquals(2, result.size());

            result = fileCabinet.findFoldersBySize(Size.SMALL.name());
            Assertions.assertEquals(1, result.size());
        }

        @Test
        @DisplayName("Find folder size in list with folders and non-nested MultiFolders")
        void findFolderSizeInListWithNonNestedMultiFolders() {
            fileCabinet = new FileCabinet(ExampleTest.NON_NESTED_MULTIFOLDER_LIST);

            List<Folder> result = fileCabinet.findFoldersBySize(Size.LARGE.name());
            Assertions.assertEquals(2, result.size());

            result = fileCabinet.findFoldersBySize(Size.MEDIUM.name());
            Assertions.assertEquals(1, result.size());

            result = fileCabinet.findFoldersBySize(Size.SMALL.name());
            Assertions.assertEquals(2, result.size());
        }

        @Test
        @DisplayName("Find folder size in list with folders and first-level nested MultiFolders")
        void findFolderSizeInListWithFirstLevelNestedMultiFolders() {
            fileCabinet = new FileCabinet(ExampleTest.FIRST_LEVEL_NESTED_MULTIFOLDER_LIST);

            List<Folder> result = fileCabinet.findFoldersBySize(Size.LARGE.name());
            Assertions.assertEquals(2, result.size());

            result = fileCabinet.findFoldersBySize(Size.MEDIUM.name());
            Assertions.assertEquals(1, result.size());

            result = fileCabinet.findFoldersBySize(Size.SMALL.name());
            Assertions.assertEquals(2, result.size());
        }

        @Test
        @DisplayName("Find folder size in deeply nested MultiFolders")
        void findFolderSizeInDeeplyNestedMultiFolders() {
            fileCabinet = new FileCabinet(ExampleTest.DEEPLY_NESTED_MULTIFOLDER_LIST);

            List<Folder> result = fileCabinet.findFoldersBySize(Size.LARGE.name());
            Assertions.assertEquals(1, result.size());

            result = fileCabinet.findFoldersBySize(Size.MEDIUM.name());
            Assertions.assertEquals(2, result.size());

            result = fileCabinet.findFoldersBySize(Size.SMALL.name());
            Assertions.assertEquals(1, result.size());
        }

        @Test
        @DisplayName("Find folder size in MultiFolder with mixed nesting levels")
        void findFolderSizeInMixedNestingLevels() {
            fileCabinet = new FileCabinet(ExampleTest.MIXED_NESTING_LEVEL_MULTIFOLDER_LIST);

            List<Folder> result = fileCabinet.findFoldersBySize(Size.LARGE.name());
            Assertions.assertEquals(4, result.size());

            result = fileCabinet.findFoldersBySize(Size.MEDIUM.name());
            Assertions.assertEquals(2, result.size());

            result = fileCabinet.findFoldersBySize(Size.SMALL.name());
            Assertions.assertEquals(1, result.size());
        }
    }

    /// test for count method

    @Nested
    @DisplayName("Tests for find folders count method")
    class FindFoldersCountTest {
        @Test
        @DisplayName("Find count folders in uninitialized list")
        void findFoldersCountInUninitializedList() {
            fileCabinet = new FileCabinet(null);

            int result = fileCabinet.count();
            Assertions.assertEquals(0, result);
        }

        @Test
        @DisplayName("Find count folders in empty list")
        void findFoldersCountInEmptyList() {
            fileCabinet = new FileCabinet(ExampleTest.EMPTY_FOLDER_LIST);

            int result = fileCabinet.count();
            Assertions.assertEquals(0, result);
        }

        @Test
        @DisplayName("Find count folders in list with single folder")
        void findFoldersCountInListWithOneFolder() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_FOLDER_LIST);

            int result = fileCabinet.count();
            Assertions.assertEquals(1, result);
        }

        @Test
        @DisplayName("Find count folders in a list with single MultiFolder")
        void findFoldersCountInListWithOneMultiFolder() {
            fileCabinet = new FileCabinet(ExampleTest.SINGLE_MULTIFOLDER_LIST);

            int result = fileCabinet.count();
            Assertions.assertEquals(1, result);
        }

        @Test
        @DisplayName("Find count folders with different size configuration and only folders in list")
        void findFoldersCountWithDifferentConfigurationOnlyFolderList() {
            fileCabinet = new FileCabinet(ExampleTest.FOLDER_LIST_WITH_ALL_SIZES);

            int result = fileCabinet.count();
            Assertions.assertEquals(3, result);
        }

        @Test
        @DisplayName("Find count folders without large size with only folders in list")
        void findFoldersCountWithDifferentConfigurationOnlyFolderListWithoutLargeSize() {
            fileCabinet = new FileCabinet(ExampleTest.FOLDER_LIST_WITHOUT_LARGE_SIZE);

            int result = fileCabinet.count();
            Assertions.assertEquals(3, result);
        }

        @Test
        @DisplayName("Find count folders in a list with folders and non-nested MultiFolders")
        void findFoldersCountInListWithNonNestedMultiFolders() {
            fileCabinet = new FileCabinet(ExampleTest.NON_NESTED_MULTIFOLDER_LIST);

            int result = fileCabinet.count();
            Assertions.assertEquals(5, result);
        }

        @Test
        @DisplayName("Find count folders in a list with folders and first-level nested MultiFolders")
        void findFoldersCountInListWithFirstLevelNestedMultiFolders() {
            fileCabinet = new FileCabinet(ExampleTest.FIRST_LEVEL_NESTED_MULTIFOLDER_LIST);

            int result = fileCabinet.count();
            Assertions.assertEquals(5, result);
        }

        @Test
        @DisplayName("Find count of folders in deeply nested MultiFolders")
        void findFoldersCountInDeeplyNestedMultiFolders() {
            fileCabinet = new FileCabinet(ExampleTest.DEEPLY_NESTED_MULTIFOLDER_LIST);

            int result = fileCabinet.count();
            Assertions.assertEquals(4, result);
        }

        @Test
        @DisplayName("Find count folders in MultiFolder with mixed nesting levels")
        void findFoldersCountInMixedNestingLevels() {
            fileCabinet = new FileCabinet(ExampleTest.MIXED_NESTING_LEVEL_MULTIFOLDER_LIST);

            int result = fileCabinet.count();
            Assertions.assertEquals(7, result);
        }
    }
}