package model;

import java.io.IOException;

public interface ISave {
    void save(String nomFic, Classement classement)throws IOException;
}
