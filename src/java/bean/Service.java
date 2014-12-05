package bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import static java.lang.System.in;
import java.util.Arrays;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author victor
 */
@ManagedBean(name = "service")
@SessionScoped
public class Service implements Serializable {

    byte[] suaVariavelBytes;
    
   private StreamedContent imagem; 

    public StreamedContent convertImage(byte[] img) throws IOException {
        System.out.println("Dentro do GET\n\n\n\n\n\n\n Dentro do GET");
        System.out.println("Byte: " + Arrays.toString(suaVariavelBytes));
        System.out.println("\n\n\n\n\n\n\n");
        return new DefaultStreamedContent(new ByteArrayInputStream(img));
      // return new DefaultStreamedContent(new ByteArrayInputStream(suaVariavelBytes));
    }

    public StreamedContent getImagem() {
        return imagem;
    }

    public void setImagem(StreamedContent imagem) {
        this.imagem = imagem;
    }
    
    

    public byte[] getSuaVariavelBytes() {
        return suaVariavelBytes;
    }

    public void setSuaVariavelBytes(byte[] suaVariavelBytes) {
     //   System.out.println("Set Sua Variavel\n\n\n\n\n\n\nSet Sua Variavel");
     //   System.out.println("Byte: " + Arrays.toString(suaVariavelBytes));
     //   System.out.println("\n\n\n\n\n\n\n");
        imagem = new DefaultStreamedContent(new ByteArrayInputStream(suaVariavelBytes));
     //   System.out.println("Acabo\n\n\n\n\n\n\n");
        this.suaVariavelBytes = suaVariavelBytes;
    }
    
    
    
}
