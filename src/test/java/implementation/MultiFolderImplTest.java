package implementation;

import org.example.Folder;
import org.example.MultiFolder;
import org.example.Size;

import java.util.ArrayList;
import java.util.List;

public class MultiFolderImplTest extends FolderImplTest implements MultiFolder {
    private final List<Folder> folders;

    public MultiFolderImplTest(String name, Size size, List<Folder> folders) {
        super(name, size);
        this.folders = folders;
    }

    public MultiFolderImplTest(String name, List<Folder> folders){
        this(name, Size.LARGE, folders);
    }

    public MultiFolderImplTest(String name, Size size){
        this(name, size, new ArrayList<>());
    }

    public MultiFolderImplTest(String name) {
        this(name, Size.LARGE, new ArrayList<>());
    }

    @Override
    public List<Folder> getFolders() {
        return folders;
    }
}
