/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Socket;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author adelali
 *
 *
 *
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    public String type;
    public Object content;

    public Message(String type, Object content) {
        this.type = type;

        this.content = content;

    }

}
