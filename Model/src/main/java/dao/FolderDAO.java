package dao;

import entities.Folder;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 12.06.13
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */
public interface FolderDAO {
    public Folder getFolder(Integer emailId);
    public Boolean insert(Folder f);

    public Boolean renameFolder(Integer folderID, String folderName);

    public Boolean deleteFolder(Integer folderId);


    public Integer getEmailIdByFolderId(Integer folderId);
}
