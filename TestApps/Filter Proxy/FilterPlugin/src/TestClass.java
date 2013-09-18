
import com.interceptor.plugin.Filterable;
import com.interceptor.plugin.example.ipfilter.IPFilter;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arpit.agarwal
 */
public class TestClass {
    public static void main(String args[]) throws UnknownHostException, IOException {
        Filterable filter = IPFilter.getInstance();
        System.out.println(filter.filter(new Socket("www.google.com", 80)));
    }
}
