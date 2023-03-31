import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        Font fonte = new Font("Comic Sans MS", Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        FontMetrics metrics = new FontMetrics(fonte) {        
        };
        String texto = "BANANA";
        Rectangle2D bounds = metrics.getStringBounds(texto, null);
        int widthInPixels = (int) bounds.getWidth();
        graphics.drawString(texto, (largura - widthInPixels) / 2, novaAltura - 100);

        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo));
    }
}
