import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.util.*;

class Question {
    String question;
    String[] options;
    int correctIndex;

    Question(String q, String[] o, int c) {
        question = q;
        options = o;
        correctIndex = c;
    }
}



class SplashScreen extends JWindow {

    public SplashScreen() {
        setSize(700, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(6, 95, 70));
        panel.setLayout(new BorderLayout());

        JLabel title = new JLabel("HISTORICAL TIMELINE CHALLENGE", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);

        JLabel loading = new JLabel("Loading Game...", SwingConstants.CENTER);
        loading.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        loading.setForeground(Color.WHITE);
       

        JProgressBar bar = new JProgressBar();
        bar.setIndeterminate(true);
        bar.setForeground(new Color(16,185,129));

        panel.add(title, BorderLayout.CENTER);
        panel.add(loading, BorderLayout.SOUTH);
        panel.add(bar, BorderLayout.NORTH);

        add(panel);
        setVisible(true);

       
        Timer splashTimer = new Timer(3000, null);
        splashTimer.addActionListener(e -> {
            splashTimer.stop();
            dispose();
            new TimelineDashboardGame();
        });
        splashTimer.setRepeats(false);
        splashTimer.start();
    }
}



public class TimelineDashboardGame extends JFrame {

    private java.util.List<Question> questions = new ArrayList<>();
    private int currentQuestion = 0;
    private int score = 0;
    private int displayedScore = 0;
    private int timeLeft = 10;

    private JLabel titleLabel, questionLabel, timerLabel;
    private JRadioButton[] optionButtons = new JRadioButton[4];
    private ButtonGroup group;
    private JButton submitBtn;
    private JPanel circlePanel;
    private RoundedPanel card;

    private JLabel emojiLabel;
    private Timer emojiTimer;
    private int emojiY;

    private Timer timer;

    public TimelineDashboardGame() {

        setTitle("Historical Timeline Challenge");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        loadQuestions();
        setContentPane(new GradientPanel());
        initUI();

        setVisible(true);
        startTimer();
        animateCardEntrance();
    }

    private void loadQuestions() {
        questions.add(new Question("When did World War I start?",
            new String[]{"1914","1939","1925","1905"},0));
    questions.add(new Question("When did World War II begin?",
            new String[]{"1918","1939","1945","1929"},1));
    questions.add(new Question("Pakistan independence?",
            new String[]{"1945","1930","1952","1947"},3));
    questions.add(new Question("Moon Landing year?",
            new String[]{"1965","1972","1969","1959"},2));
    questions.add(new Question("French Revolution?",
            new String[]{"1770","1789","1800","1755"},1));
    questions.add(new Question("Berlin Wall fell?",
            new String[]{"1989","1991","1980","1975"},0));
    questions.add(new Question("American independence?",
            new String[]{"1801","1783","1776","1765"},2));
    questions.add(new Question("Russian Revolution?",
            new String[]{"1900","1920","1935","1917"},3));
    questions.add(new Question("Titanic sank?",
            new String[]{"1912","1905","1920","1898"},0));
    questions.add(new Question("UN formed?",
            new String[]{"1939","1955","1945","1920"},2));

    questions.add(new Question("When did India gain independence?",
            new String[]{"1942","1945","1947","1950"},2));
    questions.add(new Question("When did the Cold War end?",
            new String[]{"1985","1989","1991","1995"},2));
    questions.add(new Question("When was the Magna Carta signed?",
            new String[]{"1215","1300","1100","1405"},0));
    questions.add(new Question("When did Christopher Columbus reach America?",
            new String[]{"1492","1500","1480","1512"},0));
    questions.add(new Question("When did the Great Fire of London happen?",
            new String[]{"1666","1600","1701","1555"},0));
    questions.add(new Question("When was the Declaration of Independence signed?",
            new String[]{"1775","1776","1780","1760"},1));
    questions.add(new Question("When did World War II end?",
            new String[]{"1942","1943","1945","1950"},2));
    questions.add(new Question("When was the first iPhone released?",
            new String[]{"2005","2007","2008","2006"},1));
    questions.add(new Question("When did the Soviet Union collapse?",
            new String[]{"1989","1990","1991","1992"},2));
    questions.add(new Question("When did Nelson Mandela become President?",
            new String[]{"1990","1994","1996","1992"},1));

    questions.add(new Question("When was Google founded?",
            new String[]{"1995","1998","2000","1992"},1));
    questions.add(new Question("When did Facebook launch?",
            new String[]{"2002","2004","2006","2001"},1));
    questions.add(new Question("When did the Korean War start?",
            new String[]{"1945","1950","1955","1960"},1));
    questions.add(new Question("When was the Eiffel Tower completed?",
            new String[]{"1889","1899","1875","1901"},0));
    questions.add(new Question("When did the American Civil War begin?",
            new String[]{"1861","1850","1870","1865"},0));
    questions.add(new Question("When did the American Civil War end?",
            new String[]{"1863","1865","1870","1868"},1));
    questions.add(new Question("When did the Renaissance begin?",
            new String[]{"1300","1400","1500","1200"},0));
    questions.add(new Question("When did World War I end?",
            new String[]{"1916","1918","1920","1914"},1));
    questions.add(new Question("When was YouTube founded?",
            new String[]{"2003","2005","2007","2001"},1));
    questions.add(new Question("When was Twitter launched?",
            new String[]{"2004","2006","2008","2005"},1));

    questions.add(new Question("When did the Black Death occur?",
            new String[]{"1347","1400","1500","1200"},0));
    questions.add(new Question("When did Martin Luther King Jr. give 'I Have a Dream' speech?",
            new String[]{"1961","1963","1965","1968"},1));
    questions.add(new Question("When was the Berlin Wall built?",
            new String[]{"1961","1955","1970","1980"},0));
    questions.add(new Question("When did the Vietnam War end?",
            new String[]{"1973","1975","1980","1968"},1));
    questions.add(new Question("When was NASA founded?",
            new String[]{"1958","1960","1955","1945"},0));
    questions.add(new Question("When did the Industrial Revolution start?",
            new String[]{"1700","1760","1800","1650"},1));
    questions.add(new Question("When did the Ottoman Empire fall?",
            new String[]{"1918","1923","1900","1930"},1));
    questions.add(new Question("When did the Persian Gulf War start?",
            new String[]{"1989","1990","1991","1992"},1));
    questions.add(new Question("When was the printing press invented?",
            new String[]{"1440","1500","1400","1550"},0));
    questions.add(new Question("When did the Spanish Civil War begin?",
            new String[]{"1936","1939","1920","1940"},0));

    questions.add(new Question("When was the first computer invented?",
            new String[]{"1936","1940","1920","1950"},0));
    questions.add(new Question("When did the Chernobyl disaster occur?",
            new String[]{"1984","1986","1988","1990"},1));
    questions.add(new Question("When was the Great Wall of China completed?",
            new String[]{"1644","1500","1400","1300"},0));
    questions.add(new Question("When did the Arab Spring begin?",
            new String[]{"2008","2010","2012","2005"},1));
    questions.add(new Question("When was the first Olympic Games held?",
            new String[]{"776 BC","500 BC","1000 BC","300 BC"},0));
    questions.add(new Question("When did Germany reunify?",
            new String[]{"1988","1990","1992","1985"},1));
    questions.add(new Question("When was the Titanic discovered?",
            new String[]{"1985","1990","1975","1965"},0));
    questions.add(new Question("When did the Hong Kong handover occur?",
            new String[]{"1995","1997","1999","2000"},1));
    questions.add(new Question("When was Microsoft founded?",
            new String[]{"1975","1980","1970","1965"},0));
    questions.add(new Question("When did the COVID-19 pandemic begin?",
            new String[]{"2018","2019","2020","2021"},1));
}

    private void initUI() {

        setLayout(null);

        titleLabel = new JLabel("HISTORICAL TIMELINE CHALLENGE", SwingConstants.CENTER);
        titleLabel.setBounds(0, 20, 1200, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel);

        card = new RoundedPanel(40);
        card.setBounds(150, 700, 900, 500);
        card.setLayout(null);
        card.setBackground(Color.WHITE);
        add(card);

        timerLabel = new JLabel("Time: 10");
        timerLabel.setBounds(30, 20, 200, 30);
        timerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        card.add(timerLabel);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(700, 400, 100, 35);
        submitBtn.setBackground(new Color(16, 94, 60));
        submitBtn.setForeground(Color.WHITE);
        card.add(submitBtn);

        submitBtn.addActionListener(e -> checkAnswer());

        questionLabel = new JLabel("", SwingConstants.LEFT);
        questionLabel.setBounds(30, 80, 800, 60);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        card.add(questionLabel);

        group = new ButtonGroup();

        int y = 160;
        for(int i=0;i<4;i++){
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setBounds(30, y, 300, 40);
            optionButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 18));
            optionButtons[i].setBackground(Color.WHITE);
            group.add(optionButtons[i]);
            card.add(optionButtons[i]);
            y += 60;
        }

        circlePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                int size = 180;
                int x = 10;
                int y = 10;

                g2.setColor(new Color(230,230,230));
                g2.fillOval(x,y,size,size);

                g2.setStroke(new BasicStroke(15));
                g2.setColor(new Color(16,94,60));
                int angle = (int)((double)displayedScore / questions.size() * 360);
                g2.drawArc(x,y,size,size,90,-angle);

                g2.setFont(new Font("Segoe UI", Font.BOLD, 28));
                g2.setColor(Color.BLACK);

                String text = displayedScore + "/" + questions.size();
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(text,
                        getWidth()/2 - fm.stringWidth(text)/2,
                        getHeight()/2 + fm.getHeight()/4);
            }
        };

        circlePanel.setBounds(650, 50, 200, 200);
        circlePanel.setOpaque(false);
        card.add(circlePanel);

        emojiLabel = new JLabel("", SwingConstants.CENTER);
        emojiLabel.setBounds(400, 300, 100, 100);
        emojiLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 70));

        emojiLabel.setVisible(false);
        card.add(emojiLabel);

        showQuestion();
    }

    private void showEmoji(boolean correct) {
        emojiLabel.setText(correct ? "😊" : "😢");
        emojiLabel.setForeground(correct ? new Color(255, 193, 7) : new Color(255, 193, 7));
        emojiY = 300;
        emojiLabel.setLocation(400, emojiY);
        emojiLabel.setVisible(true);

        emojiTimer = new Timer(15, e -> {
            if (emojiY > 200) {
                emojiY -= 2;
                emojiLabel.setLocation(400, emojiY);
            } else {
                emojiTimer.stop();
                new Timer(800, ev -> {
                    emojiLabel.setVisible(false);
                    ((Timer) ev.getSource()).stop();
                }).start();
            }
        });
        emojiTimer.start();
    }

    private void animateScore() {
        Timer t = new Timer(20, e -> {
            if (displayedScore < score) {
                displayedScore++;
                circlePanel.repaint();
            } else {
                ((Timer)e.getSource()).stop();
            }
        });
        t.start();
    }

    private void animateCardEntrance() {
        Timer slide = new Timer(5, e -> {
            Point p = card.getLocation();
            if (p.y > 120) {
                card.setLocation(p.x, p.y - 10);
            } else {
                ((Timer)e.getSource()).stop();
            }
        });
        slide.start();
    }

    private void startTimer(){
        timer = new Timer(1000,e->{
            timeLeft--;
            timerLabel.setText("Time: "+timeLeft);
            if(timeLeft<=0){
                nextQuestion();
            }
        });
        timer.start();
    }

    private void showQuestion(){
        if(currentQuestion >= questions.size()){
            endGame();
            return;
        }
        Question q = questions.get(currentQuestion);
        questionLabel.setText((currentQuestion+1)+". "+q.question);
        for(int i=0;i<4;i++){
            optionButtons[i].setText(q.options[i]);
            optionButtons[i].setSelected(false);
        }
        timeLeft = 15;
    }

    private void checkAnswer(){
        Question q = questions.get(currentQuestion);
        boolean correct = false;

        for(int i=0;i<4;i++){
            if(optionButtons[i].isSelected() && i == q.correctIndex){
                score++;
                animateScore();
                correct = true;
            }
        }

        showEmoji(correct);

        new Timer(1000, e -> {
            ((Timer)e.getSource()).stop();
            nextQuestion();
        }).start();
    }

   private void nextQuestion(){
    currentQuestion++;
    timer.stop();         
    timeLeft = 15;         
    startTimer();          
    showQuestion();
}

    private void endGame(){
        timer.stop();
        JOptionPane.showMessageDialog(this,
                "Game Over!\nFinal Score: "+score+"/"+questions.size());
        System.exit(0);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(SplashScreen::new);
    }
}



class GradientPanel extends JPanel {
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new GradientPaint(0,0,new Color(16,185,129),
                getWidth(),getHeight(),new Color(6,95,70)));
        g2.fillRect(0,0,getWidth(),getHeight());
    }
}

class RoundedPanel extends JPanel {
    private int radius;
    public RoundedPanel(int r){
        radius = r;
        setOpaque(false);
    }
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.fillRoundRect(0,0,getWidth(),getHeight(),radius,radius);
    }
}