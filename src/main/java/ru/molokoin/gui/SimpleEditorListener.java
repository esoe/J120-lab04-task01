package ru.molokoin.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Класс поддерживает обработку событий WindowEvent и ActionEvent, возникающих в
 * классе SimpleEditor.
 * @author 
 */
public class SimpleEditorListener extends WindowAdapter implements ActionListener, AutoCloseable{
    /**
     * Ссылка на главное окно приложения.
     */
    private SimpleEditor editor;
    /**
     * Ссылка на текущий файл, открытый в приложении.
     */
    private File file;
    /**
     * Ссылки на потоки ввода/вывода, связанные с текущим файлом.
     */
    private FileReader reader;
    private FileWriter writer;

    /**
     * Конструктор класса, определяющий ссылку на главное окно приложения.
     * @param editor ссылка на главное окно приложения.
     */
    public SimpleEditorListener(SimpleEditor editor) {
        this.editor = editor;
    }
    /**
     * Метод обеспечивает обработку событий ActionEvent, связанных с кнопками и
     * пунктами меню класса SimpleEditor.
     * @param ae ссылка на объект, описывающий событие.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case "Open":
                System.out.println("> open ...");
                open();
                
                break;
            case "Save":
                System.out.println("> save ...");
                // Обработка команды на сохранение результатов редактирования
                // в открытом файле.
                // проверка наличия открытого файла
                if(!file.exists()) try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                //проверка доступности файла к записи
                if(file.canWrite()){
                    String line = editor.getTextArea().getText();
                    try (FileWriter writer = new FileWriter(file, Charset.forName("utf-8"), false)) {
                        this.writer = writer;
                        writer.append(line);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case"Cancel":
                System.out.println("> cancel ...");
                cancel();
                break;
            case "Exit":
                System.out.println("> exit ...");
                // Обработка команды на закрытие приложения. При обработке этой команды следует учесть, что в момент её подачи в приложении может быть открыт какой-то файл. В этом случае пользователю с помощью стандартного диалогового окна класса JOptionPane должен предлагаться выбор между закрытием этого файла с сохранением или без сохранения сделанных в этом файле изменений.
                close();
        }
    }
    /**
     * Метод, автоматически вызываемый при удалении объекта из памяти (обычно
     * при закрытии приложения). Следует учесть, что в момент его вызова в
     * приложении может оставаться открытым какой-то файл.
     */
    @Override
    public void close() {
        String message = "Нажмите чтонибудь ...";
        int result = JOptionPane.showConfirmDialog(editor, message, "Завершение работы", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            System.out.println("Выбрано : Завершение работы программы ...");
            editor.dispose();
            System.out.println("Работа программы завершена.");
        }
        if (result == JOptionPane.CANCEL_OPTION){
            System.out.println("Выбрано : Продолжение работы программы ...");
        }
    }
    /**
     * Обработка команды на открытие редактируемого файла. При обработке этой команды следует использовать классы:
     * - java.io.FileReader;
     * - javax.swing.JFileChooser;
     * - javax.swing.JOptionPane.
     * Следует учесть, что при подаче этой команды в приложении уже может быть открыт какой-то файл.
     * Все выброшенные исключения в данном фрагменте должны быть обработаны, при этом пользователю должны сообщаться причины исключения с помощью стандартных диалоговых окон класса JOptionPane
     * 
     */
    public void open(){
        if (!file.exists()){
            Path path = Paths.get("");
            JFileChooser fileChooser = new JFileChooser(path.toAbsolutePath().toString());
            fileChooser.setDialogTitle("Выбор директории");
            // Определение режима - только файлы
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(fileChooser);
            // Если директория выбрана, выведем ее адрес в консоль
            if (result == JFileChooser.APPROVE_OPTION ){
                file = fileChooser.getSelectedFile();
                System.out.println("Выбран файл: " + file.getAbsolutePath());
            }
            if(file.canRead()){
                System.out.println("Содержимое файла:");
                editor.getTextArea().setText(null);
                try(FileReader reader = new FileReader(file, Charset.forName("utf-8"))){
                    this.reader = reader;
                    System.out.println("Кодировка файла: " + reader.getEncoding());
                    char[]buf = new char[5];
                    int c =0;
                    while((c=reader.read(buf))!=-1){
                        editor.getTextArea().append(new String(buf, 0, c));
                    }
                }catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(editor.getTextArea().getText());
            }
        }else{
            // StringBuilder sb = new StringBuilder();
            // String message = "Нажмите чтонибудь ...";
            // int result = JOptionPane.showConfirmDialog(editor, message, "Завершение работы", JOptionPane.OK_CANCEL_OPTION);
            // if (result == JOptionPane.OK_OPTION){
            //     System.out.println("Выбрано : Завершение работы программы ...");
            //     editor.dispose();
            //     System.out.println("Работа программы завершена.");
            // }
            // if (result == JOptionPane.CANCEL_OPTION){
            //     System.out.println("Выбрано : Продолжение работы программы ...");
            // }
        }
    }
    /**
     * закрытие открытого файла без сохранения сделанных изменений
     */
    public void cancel(){
        file = null;
        editor.getTextArea().setText(null);
    }
}
