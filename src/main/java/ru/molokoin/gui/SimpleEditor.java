package ru.molokoin.gui;

import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Класс представляет главное окно приложения, реализующего простой текстовый редактор.
 * Класс обеспечивает возможность открытия или создания файла,
 * содержащего простой текст, например, в формате .txt, .html или xml, с
 * возможностью редактирования его содержания.
 * @author
 */
public class SimpleEditor extends JFrame{
    private Container cp;
    /**
     * Надпись, отображающая имя текущего/редактируемого файла, а также метку,
     * отражающую его текущее состояние. Например, если текст изменён, но не
     * сохранён, то в данном поле ставиться специальный маркер (по выбору программиста)
     * или меняется шрифт отображения имени файла.
     */
    private JLabel fileName;

    /**
     * Окно редактирования, в котором отображается и редактируется текст файла.
     */
    private JTextArea text;
    /**
     * Главное меню приложения.
     * 
     */
    private JMenuBar bar;
    /**
     * Массив пунктов главного меню приложения, который должен включать "File" и
     * "Edit".
     */
    private JMenu[] menu;
    /**
     * Массив команд главного меню приложения, который должен включать команды
     * "Open", "Save", "Cancel"и "Exit".
     */
    private JMenuItem[] commandMenu;
    /**
     * Массив кнопок интерфейса "Open", "Save", "Cancel" и "Exit", которые
     * дублируют команды главного меню приложения.
     */
    private JButton[] commandButton;
    /**
     * Ссылка на обработчик команд пользовательского интерфейса.
     */
    private SimpleEditorListener listener;
    /**
     * Конструктор приложения.
     */
    public SimpleEditor() {
        setTitle("Simple text editor");
        init();
        createMenu();
        setVisible(true);
    }

    /**
     * Стартовый метод приложения.
     * @paramargs параметры командной строки.
     */
    public static void main(String[] args) {
        SimpleEditor simpleEditor = new SimpleEditor();
    }

    /**
     * Метод инициализирует обработчик событий listener, создаёт и настраивает
     * все элементы пользовательского интерфейса.
     */
    private void init() {
        //настройки фрейма
        setSize(800, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //инициируем панель контента, для размещения элементов графического интерфейса
        cp = getContentPane();
        // инициируем слушатель событий
        listener = new SimpleEditorListener(this);
        // Реализация метода
        text = new JTextArea();
        cp.add(text);
    }

    /**
     * Метод полностью создаёт главное меню приложения и добавляет его в главное
     * окно приложения.
     */
    private void createMenu() {
        // Реализация метода
        bar = new JMenuBar();
        menu = new JMenu[]{new JMenu("File"), new JMenu("Edit")};
        commandMenu = new JMenuItem[]{new JMenuItem("Open"), new JMenuItem("Save"), new JMenuItem("Cancel"), new JMenuItem("Exit")};
        //добавляем команды в меню "File"
        for (int i=0; i < 4; i++){
            commandMenu[i].addActionListener(listener);
            menu[0].add(commandMenu[i]);
        }
        //добавляем менюшки в основное меню
        for (int i=0; i < 2; i++){
            bar.add(menu[i]);
        }
        //установили главное меню редактора
        setJMenuBar(bar);
        
    }

    /**
     * Метод обеспечивает добавление или замену текста в окне редактирования.
     * @paramstr добавляемый текст.
     * @paramappend значение trueо значает, что текст добавляется к тексту,
     * который содержится в поле. Значение false означает, что текст в поле
     * полностью заменяется на значение str.
     */
    void appendText(String str, boolean append) {
        // Реализация метода
    }

    /**
     * Метод возвращает весь текст, содержащийся в окне редактирования.
     * @return текст из окна редактирования.
     */
    String getText() {
        // Реализация метода
        return null;
    }
    
    public JTextArea getTextArea(){
        return text;
    }


    
}
