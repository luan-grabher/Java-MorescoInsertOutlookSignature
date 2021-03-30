package insertoutlooksignature;

import fileManager.FileManager;
import java.io.File;
import javax.swing.JOptionPane;

public class InsertOutlookSignature {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String completeName = JOptionPane.showInputDialog(null, "Digite o nome completo:", "Nome Completo", JOptionPane.QUESTION_MESSAGE);

        //Verifica nome em branco
        if (!completeName.isBlank()) {
            String[] departments = new String[]{"Contábil", "Fiscal", "Societário", "Pessoal", "Administrativo", "TI", "Diretoria"};

            String department = (String) JOptionPane.showInputDialog(null, "Qual departamento?", "Departamento", JOptionPane.QUESTION_MESSAGE, null, departments, 0);
            department = "Departamento " + department;

            //Pega assinatura padrão
            String defaultSignatureText = FileManager.getText(FileManager.getFile("defaultSignature.htm"));
            String newSignatureText = defaultSignatureText.replaceAll(":completeName", completeName);
            newSignatureText = newSignatureText.replaceAll(":department", department);

            //Salva na pasta            
            File outlookSignatureFolder = new File(System.getProperty("user.home") + "\\AppData\\Roaming\\Microsoft\\Assinaturas\\");
            String fileName = completeName + ".htm";

            if (FileManager.save(outlookSignatureFolder, fileName, newSignatureText)) {
                JOptionPane.showMessageDialog(null, "A assinatura para " + completeName + " do " + department + ", foi inserida no Outlook. Dentro do Outlook defina a assinatura como padrão para utiliza-la.");
            } else {
                JOptionPane.showMessageDialog(null, "Ocorreu algum erro ao tentar salvar, você pode executar novamente?");
            }
        } else {
            JOptionPane.showMessageDialog(null, "O nome completo não pode ficar em branco!");
        }
    }

}
