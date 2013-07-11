package dao;

import entities.Letter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 13.06.13
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
public interface LetterDAO {
    //get all Incoming Letters by folderID
    public List<Letter> getLetters(Integer folderId);
    //get all Outgoing Letters by folderID
    public List<Letter> getOutLetters(Integer folderId);

    public Boolean insert(Letter l);

    //delete entities.Letter from DB by letterId
    public Boolean delete(Integer id);

    //change entities.Letter attributes (toEmail and LetterOwnerTo)
    public Boolean changeAttr(Integer letterId, String uniqAddr, Integer folderId);

//    //delete AllLetters(where LetterOwnerTo = folderId) from Db by folderId
//    public Boolean deleteAllLetters(Integer folderId);
}
