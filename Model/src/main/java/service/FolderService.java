package service;

import entities.Folder;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 14.06.13
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface FolderService {
    public Folder getFolder(Integer emailId);

    public Boolean createFolder(Integer userId, String folderName);

    public Boolean renameFolder(Integer folderID, String folderName);

    public Boolean deleteFolder(Integer folderId, Integer emailId);
}
