package implementation;

import org.example.Folder;
import org.example.Size;

public class FolderImplTest implements Folder {
    private final String name;
    private final Size size;

    public FolderImplTest(String name, Size size){
        this.name = name;
        this.size = size;
    }

    public FolderImplTest(String name){
        this(name, Size.LARGE);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSize() {
        return size.name();
    }
}
