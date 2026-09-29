package uzv.alter.cinema.client;

import uzv.alter.cinema.api.init.SessionInit;
import uzv.alter.cinema.common.db.TranslationCore;
import uzv.alter.cinema.common.film.Film;
import uzv.alter.cinema.common.hall.Hall;
import uzv.alter.cinema.common.seat.ISeatable;
import uzv.alter.cinema.common.session.Session;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaFrame extends JFrame {

    private JComboBox<Session> sessionComboBox;
    private JComboBox<String> timeSlotComboBox;
    private JComboBox<String> languageComboBox;

    private JPanel hallPanelContainer;
    private JTextArea selectedSeatsArea;
    private JLabel totalPriceLabel;

    private JLabel titleLabel;
    private JLabel subtitleLabel;
    private JLabel footerLabel;
    private JLabel sidebarTitle;
    private JLabel totalTitle;
    private JLabel noteLabel;
    private JButton bookButton;

    private Session currentSession;
    private int currentSessionIndex = 0;
    private boolean isRebuildingUI = false;

    private record SelectedSeatInfo(int row, int col, ISeatable seatType, float finalPrice) {}

    private final List<SelectedSeatInfo> selectedSeats = new ArrayList<>();

    public CinemaFrame() {
        super(uzv.alter.cinema.common.translate.Component.of("app.title").getString());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1150, 750);
        setMinimumSize(new Dimension(1000, 680));
        setLocationRelativeTo(null);

        currentSession = SessionInit.SESSION_A;

        initUI();
        updateTimeSlots();
        reloadHallUI();
    }

    private void initUI() {
        getContentPane().removeAll();

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        mainPanel.setBackground(new Color(245, 247, 250));

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
        contentPanel.setOpaque(false);

        hallPanelContainer = new JPanel(new BorderLayout());
        hallPanelContainer.setOpaque(false);

        selectedSeatsArea = new JTextArea(5, 18);
        totalPriceLabel = new JLabel("0 ₸");

        contentPanel.add(hallPanelContainer, BorderLayout.CENTER);
        contentPanel.add(createSidebarPanel(), BorderLayout.EAST);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        footerLabel = new JLabel(uzv.alter.cinema.common.translate.Component.of("app.footer").getString());
        footerLabel.setForeground(new Color(140, 145, 155));
        footerLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);
        revalidate();
        repaint();
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);

        titleLabel = new JLabel(uzv.alter.cinema.common.translate.Component.of("app.title").getString());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(new Color(30, 35, 45));

        subtitleLabel = new JLabel(uzv.alter.cinema.common.translate.Component.of("app.subtitle").getString());
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(130, 135, 145));

        sessionComboBox = new JComboBox<>(new Session[]{
                SessionInit.SESSION_A,
                SessionInit.SESSION_B,
                SessionInit.SESSION_C
        });
        sessionComboBox.setSelectedItem(currentSession);
        sessionComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Session s) {
                    setText(s.getFilmName().getString());
                }
                return this;
            }
        });

        timeSlotComboBox = new JComboBox<>();

        languageComboBox = new JComboBox<>(new String[]{"ru", "en", "kk"});
        languageComboBox.setSelectedItem(TranslationCore.getCurrent());
        languageComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof String langCode) {
                    setText(uzv.alter.cinema.common.translate.Component.of("lang." + langCode).getString());
                }
                return this;
            }
        });

        sessionComboBox.addActionListener(e -> {
            if (isRebuildingUI) return;
            Session selected = (Session) sessionComboBox.getSelectedItem();
            if (selected != null && selected != currentSession) {
                currentSession = selected;
                currentSessionIndex = 0;
                selectedSeats.clear();
                updateTimeSlots();
                reloadHallUI();
            }
        });

        timeSlotComboBox.addActionListener(e -> {
            if (isRebuildingUI) return;
            int idx = timeSlotComboBox.getSelectedIndex();
            if (idx >= 0 && idx != currentSessionIndex) {
                currentSessionIndex = idx;
                selectedSeats.clear();
                reloadHallUI();
            }
        });

        languageComboBox.addActionListener(e -> {
            if (isRebuildingUI) return;
            String selectedLang = (String) languageComboBox.getSelectedItem();
            if (selectedLang != null && !selectedLang.equals(TranslationCore.getCurrent())) {
                TranslationCore.setCurrent(selectedLang);
                rebuildUI();
            }
        });

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 5));
        filterPanel.setOpaque(false);

        String cardFilm = uzv.alter.cinema.common.translate.Component.of("card.film").getString();
        String cardDateTime = uzv.alter.cinema.common.translate.Component.of("card.datetime").getString();
        String cardLang = uzv.alter.cinema.common.translate.Component.of("card.lang").getString();

        filterPanel.add(createSelectCard(cardFilm, sessionComboBox, 220));
        filterPanel.add(createSelectCard(cardDateTime, timeSlotComboBox, 180));
        filterPanel.add(createSelectCard(cardLang, languageComboBox, 130));

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createVerticalStrut(4));
        headerPanel.add(subtitleLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(filterPanel);

        return headerPanel;
    }

    private JPanel createSelectCard(String labelText, JComponent combo, int width) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 235, 242), 1, true),
                new EmptyBorder(4, 10, 6, 10)
        ));

        JLabel label = new JLabel(labelText.toUpperCase());
        label.setFont(new Font("SansSerif", Font.BOLD, 10));
        label.setForeground(new Color(150, 155, 170));

        combo.setPreferredSize(new Dimension(width, 26));
        combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        combo.setBackground(Color.WHITE);

        card.add(label);
        card.add(Box.createVerticalStrut(2));
        card.add(combo);

        return card;
    }

    private void updateTimeSlots() {
        timeSlotComboBox.removeAllItems();
        if (currentSession == null) return;

        for (int i = 0; i < currentSession.getTimes().size(); i++) {
            String dateStr = currentSession.getDate(i).getString();
            String timeStr = currentSession.getTime(i).getString();
            timeSlotComboBox.addItem(dateStr + " " + timeStr);
        }

        if (currentSessionIndex >= 0 && currentSessionIndex < timeSlotComboBox.getItemCount()) {
            timeSlotComboBox.setSelectedIndex(currentSessionIndex);
        }
    }

    private void reloadHallUI() {
        hallPanelContainer.removeAll();

        if (currentSession == null || currentSession.getHalls().isEmpty()) {
            hallPanelContainer.revalidate();
            hallPanelContainer.repaint();
            return;
        }

        Hall currentHall = currentSession.getHalls().get(currentSessionIndex);
        int[][] layout = currentHall.getLayout();
        List<ISeatable> seatTypes = currentHall.getSeatTypes();

        JPanel hallPanel = new JPanel(new BorderLayout(12, 12));
        hallPanel.setBackground(Color.WHITE);
        hallPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 235, 242), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        JLabel screenLabel = new JLabel(uzv.alter.cinema.common.translate.Component.of("hall.screen").getString(), SwingConstants.CENTER);
        screenLabel.setOpaque(true);
        screenLabel.setBackground(new Color(242, 244, 250));
        screenLabel.setForeground(new Color(130, 135, 150));
        screenLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        screenLabel.setBorder(new EmptyBorder(8, 0, 8, 0));
        hallPanel.add(screenLabel, BorderLayout.NORTH);

        int rows = layout.length;
        int cols = layout[0].length;

        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        gbc.fill = GridBagConstraints.BOTH;

        Film film = currentSession.getFilm();

        for (int r = 0; r < rows; r++) {
            gbc.gridx = 0;
            gbc.gridy = r;
            gbc.weightx = 0.0;
            gbc.weighty = 1.0;
            JLabel rowLabel = new JLabel(String.valueOf(r + 1), SwingConstants.CENTER);
            rowLabel.setForeground(new Color(150, 155, 165));
            rowLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            rowLabel.setPreferredSize(new Dimension(24, 24));
            gridPanel.add(rowLabel, gbc);

            for (int c = 0; c < cols; c++) {
                int seatTypeIndex = layout[r][c];
                ISeatable seatType = seatTypes.get(seatTypeIndex);

                JToggleButton seatButton = new JToggleButton(String.valueOf(c + 1)) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                        g2.setColor(getBackground());
                        g2.fillRect(0, 0, getWidth(), getHeight());

                        g2.dispose();
                        super.paintComponent(g);
                    }
                };

                seatButton.setFocusPainted(false);
                seatButton.setBorderPainted(true);
                seatButton.setContentAreaFilled(false);
                seatButton.setMargin(new Insets(0, 0, 0, 0));
                seatButton.setFont(new Font("SansSerif", Font.PLAIN, 10));

                boolean isLongSeat = seatType.getHeight() > 1.0f || seatType.getPriceModifier() > 1.0f;

                int baseWidth = 32;
                int baseHeight = isLongSeat ? (int) (32 * Math.max(1.5f, seatType.getHeight())) : 32;
                seatButton.setPreferredSize(new Dimension(baseWidth, baseHeight));

                float finalPrice = film.costOfTicket() * seatType.getPriceModifier();
                seatButton.setToolTipText(seatType.getName().getString() + " (" + (int) finalPrice + " ₸)");

                int rowNum = r + 1;
                int colNum = c + 1;

                boolean isAlreadySelected = selectedSeats.stream()
                        .anyMatch(s -> s.row() == rowNum && s.col() == colNum);

                if (isAlreadySelected) {
                    seatButton.setSelected(true);
                }

                applySeatStyle(seatButton, seatType, isAlreadySelected);

                seatButton.addActionListener(e -> {
                    SelectedSeatInfo seatInfo = new SelectedSeatInfo(rowNum, colNum, seatType, finalPrice);
                    boolean isSelected = seatButton.isSelected();

                    applySeatStyle(seatButton, seatType, isSelected);

                    if (isSelected) {
                        selectedSeats.add(seatInfo);
                    } else {
                        selectedSeats.removeIf(s -> s.row() == rowNum && s.col() == colNum);
                    }
                    updateBookingInfo();
                });

                gbc.gridx = c + 1;
                gbc.gridy = r;
                gbc.weightx = 1.0;
                gbc.weighty = isLongSeat ? 1.5 : 1.0;

                gridPanel.add(seatButton, gbc);
            }
        }

        hallPanel.add(gridPanel, BorderLayout.CENTER);
        hallPanelContainer.add(hallPanel, BorderLayout.CENTER);

        updateBookingInfo();

        hallPanelContainer.revalidate();
        hallPanelContainer.repaint();
    }

    private void applySeatStyle(JToggleButton button, ISeatable seatType, boolean isSelected) {
        boolean isLongSeat = seatType.getHeight() > 1.0f || seatType.getPriceModifier() > 1.0f;

        if (isSelected) {
            if (isLongSeat) {
                button.setBackground(new Color(225, 181, 101));
                button.setForeground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(new Color(152, 103, 53), 1));
            } else {
                button.setBackground(new Color(156, 110, 213));
                button.setForeground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(new Color(112, 64, 152), 1));
            }
        } else {
            if (isLongSeat) {
                button.setBackground(new Color(240, 244, 255));
                button.setForeground(new Color(30, 41, 59));
                button.setBorder(BorderFactory.createLineBorder(new Color(180, 198, 252), 1));
            } else {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(51, 65, 85));
                button.setBorder(BorderFactory.createLineBorder(new Color(226, 232, 240), 1));
            }
        }
    }

    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(270, 0));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 235, 242), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        sidebarTitle = new JLabel(uzv.alter.cinema.common.translate.Component.of("booking.your_choice").getString());
        sidebarTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        sidebarTitle.setForeground(new Color(30, 35, 45));

        selectedSeatsArea.setEditable(false);
        selectedSeatsArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
        selectedSeatsArea.setForeground(new Color(50, 55, 70));
        selectedSeatsArea.setOpaque(false);

        totalTitle = new JLabel(uzv.alter.cinema.common.translate.Component.of("booking.total").getString());
        totalTitle.setForeground(new Color(140, 145, 155));
        totalTitle.setFont(new Font("SansSerif", Font.PLAIN, 13));

        totalPriceLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        totalPriceLabel.setForeground(new Color(110, 68, 204));

        bookButton = new JButton(uzv.alter.cinema.common.translate.Component.of("booking.button").getString());
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        bookButton.setBackground(new Color(110, 68, 204));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFocusPainted(false);
        bookButton.setBorderPainted(false);
        bookButton.setOpaque(true);
        bookButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bookButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));

        noteLabel = new JLabel(uzv.alter.cinema.common.translate.Component.of("booking.note").getString());
        noteLabel.setForeground(new Color(150, 155, 165));
        noteLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

        sidebar.add(sidebarTitle);
        sidebar.add(Box.createVerticalStrut(15));

        JScrollPane scrollPane = new JScrollPane(selectedSeatsArea);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        sidebar.add(scrollPane);

        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(new JSeparator());
        sidebar.add(Box.createVerticalStrut(15));
        sidebar.add(totalTitle);
        sidebar.add(totalPriceLabel);
        sidebar.add(Box.createVerticalStrut(18));
        sidebar.add(bookButton);
        sidebar.add(Box.createVerticalStrut(6));
        sidebar.add(noteLabel);

        return sidebar;
    }

    private void updateBookingInfo() {
        StringBuilder sb = new StringBuilder();
        float total = 0;

        for (SelectedSeatInfo info : selectedSeats) {
            String rowText = uzv.alter.cinema.common.translate.Component.of("seat.row", info.row()).getString();
            String seatText = uzv.alter.cinema.common.translate.Component.of("seat.number", info.col()).getString();

            sb.append(rowText).append(" · ").append(seatText)
                    .append(" (").append((int) info.finalPrice()).append(" ₸)\n");
            total += info.finalPrice();
        }

        selectedSeatsArea.setText(sb.toString());
        totalPriceLabel.setText(String.format("%,d ₸", (int) total));
    }

    private void rebuildUI() {
        isRebuildingUI = true;

        int savedSlotIndex = currentSessionIndex;

        setTitle(uzv.alter.cinema.common.translate.Component.of("app.title").getString());
        initUI();

        currentSessionIndex = savedSlotIndex;
        updateTimeSlots();
        reloadHallUI();

        isRebuildingUI = false;
    }
}