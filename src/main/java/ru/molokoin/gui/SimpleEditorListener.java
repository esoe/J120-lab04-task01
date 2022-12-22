package ru.molokoin.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
// Импорт классов, которые используются при решении данной задачи.
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
            case "open":
                // Обработка команды на открытие редактируемого файла. При
                // обработке этой команды следует использовать классы:
                // java.io.FileReader;
                // javax.swing.JFileChooser;
                // javax.swing.JOptionPane.
                // Следует учесть, что при подаче этой команды в приложении
                // уже может быть открыт какой-то файл.
                // Все выброшенные исключения в данном фрагменте должны быть
                // обработаны, при этом пользователю должны сообщаться причины
                // исключения с помощью стандартных диалоговых окон класса
                // JOptionPane
                break;
            case "save":
                // Обработка команды на сохранение результатов редактирования
                // воткрытомфайле.
                break;
            case"cancel":
                // Обработка команды на закрытие открытого файла без сохранения
                // сделанных изменений.
                break;
            case "exit":
                // Обработка команды на закрытие приложения. При обработке этой
                // команды следует учесть, что в момент её подачи в приложении
                // может быть открыт какой-то файл. В этом случае пользователю
                // с помощью стандартного диалогового окна класса JOptionPane
                // должен предлагаться выбор между закрытием этого файла с
                // сохранением или без сохранения сделанных в этом файле
                // изменений. 
        }
    }
    /**
     * Метод, автоматически вызываемый при удалении объекта из памяти (обычно
     * при закрытии приложения). Следует учесть, что в в момент его вызова в
     * приложении может оставаться открытым какой-то файл.
     */
    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
