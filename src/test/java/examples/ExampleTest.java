package examples;

import implementation.FolderImplTest;
import implementation.MultiFolderImplTest;
import org.example.Folder;
import org.example.Size;

import java.util.ArrayList;
import java.util.List;

public class ExampleTest {

    public static final List<Folder> EMPTY_FOLDER_LIST = new ArrayList<>();

    public static final List<Folder> SINGLE_FOLDER_LIST = List.of(new FolderImplTest("folder1"));

    public static final List<Folder> SINGLE_MULTIFOLDER_LIST = List.of(new MultiFolderImplTest("folder1"));

    public static final List<Folder> FOLDER_LIST_WITH_ALL_SIZES = List.of(
            new FolderImplTest("folder1"),
            new FolderImplTest("folder2", Size.MEDIUM),
            new FolderImplTest("folder3", Size.SMALL));


    public static final List<Folder> FOLDER_LIST_WITHOUT_LARGE_SIZE = List.of(
            new FolderImplTest("folder1", Size.SMALL),
            new FolderImplTest("folder2", Size.MEDIUM),
            new FolderImplTest("folder3", Size.MEDIUM));

    public static final List<Folder> NON_NESTED_MULTIFOLDER_LIST = List.of(
            new FolderImplTest("folder1", Size.SMALL),
            new FolderImplTest("folder2", Size.SMALL),
            new FolderImplTest("folder3"),
            new MultiFolderImplTest("folder4", Size.MEDIUM),
            new MultiFolderImplTest("folder5"));

    public static final List<Folder> FIRST_LEVEL_NESTED_MULTIFOLDER_LIST = List.of(
            new MultiFolderImplTest("folder4", Size.SMALL,
                    List.of(
                            new FolderImplTest("folder1"),
                            new FolderImplTest("folder2", Size.SMALL))),
            new MultiFolderImplTest("folder5",
                    List.of(
                            new FolderImplTest("folder3", Size.MEDIUM))));

    public static final List<Folder> DEEPLY_NESTED_MULTIFOLDER_LIST = List.of(
            new MultiFolderImplTest("folder1",
                    List.of(
                            new MultiFolderImplTest("folder2", Size.MEDIUM,
                                    List.of(
                                            new MultiFolderImplTest("folder3", Size.MEDIUM,
                                                    List.of(
                                                            new FolderImplTest("folder4", Size.SMALL))))))));


    public static final List<Folder> MIXED_NESTING_LEVEL_MULTIFOLDER_LIST = List.of(
            new FolderImplTest("folder1"),
            new MultiFolderImplTest("folder2",
                    List.of(
                            new FolderImplTest("folder3"),
                            new MultiFolderImplTest("folder4", Size.MEDIUM,
                                    List.of(
                                            new FolderImplTest("folder5", Size.MEDIUM),
                                            new MultiFolderImplTest("folder6",
                                                    List.of(
                                                            new FolderImplTest("folder7", Size.SMALL))))))));
}
