package org.jnode.fs.hfsplus;

import javax.naming.NameNotFoundException;

import org.jnode.driver.Device;
import org.jnode.fs.FileSystemException;
import org.jnode.fs.Formatter;
import org.jnode.fs.service.FileSystemService;
import org.jnode.naming.InitialNaming;

public class HfsPlusFileSystemFormatter extends Formatter<HfsPlusFileSystem> {

	protected HfsPlusFileSystemFormatter() {
		super(new HfsPlusFileSystemType());
	}

	@Override
	public HfsPlusFileSystem format(Device device) throws FileSystemException {
		try {
            FileSystemService fSS = InitialNaming.lookup(FileSystemService.NAME);
            HfsPlusFileSystemType type = fSS.getFileSystemType(HfsPlusFileSystemType.ID);
            HfsPlusFileSystem fs = type.create(device, false);
            fs.create();
            return fs;
        } catch (NameNotFoundException e){
    	   	throw new FileSystemException(e);
        }
	}

}