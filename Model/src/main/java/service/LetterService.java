package service;

import entities.Letter;
import entities.User;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * entities.User: dkorolev
 * Date: 13.06.13
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface LetterService {
    public List<Letter> getLetters(User u);
    public List<Letter> getOutLetters(User u);

    public List<Letter> getLetters(Integer folderInId);
    public List<Letter> getOutLetters(Integer folderOutId);

    public Boolean insert(Letter l);

    public Boolean delete(Integer id);

    public Boolean moveLetterToFolder(ArrayList<Integer> ls, Integer folderId);
}
