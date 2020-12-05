package amber.NoName_1000.tcp_messenger;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class amber_index {
	
	static String Wersja = "v1.0 Alpha (build 0.00001)";
	
	static ServerSocket serwer_tcp;
	static boolean ServerOpened = false;
	static boolean ServerConnected = false;
	static boolean PasswordAuth = false;
	static Socket connectionSC;
	static JTextArea chat_frame;
	static JScrollPane chat_frame_2;
	static Socket tcp_connect;
	static String password;
	static String PasswordHash;
	
	public static void main(String[] args) throws IOException {
		
		// Ramka
		JFrame ramka = new JFrame("[Amber] TCP Messenger.");
		ramka.setSize(800,600);
		ramka.setLayout(null);
		ramka.setResizable(false);
		ramka.setLocationRelativeTo(null);
		ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			ramka.setContentPane(new JLabel(new ImageIcon(ImageIO.read(amber_index.class.getResource("/Amber_RESOURCES/AMBER_material_background.jpg")))));
		} catch (IOException e122) {}
		
		
		// Style
		Font styl1 = new Font("Courier New", Font.BOLD, 25);
		Font styl2 = new Font("Courier New", Font.BOLD, 20);
		Font styl3 = new Font("Courier New", Font.BOLD, 15);
		Font styl4 = new Font("Arial", Font.PLAIN, 15);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		// Panel
		
		JRootPane logo = new JRootPane();
		logo.setBounds(218,43,68,68);
		logo.setBackground(Color.WHITE);
		logo.setOpaque(true);
		logo.setBorder(border);
		
		try {
			logo.setContentPane(new JLabel(new ImageIcon(ImageIO.read(amber_index.class.getResource("/Amber_RESOURCES/AMBER_chat.png")))));
		} catch (IOException e) {}
		
		try {
			ramka.setIconImage(ImageIO.read(amber_index.class.getResource("/Amber_RESOURCES/AMBER_chat.png")));
		} catch (IOException e) {}
		
		//Ikonki
		Icon ikonka_serwer = new ImageIcon(ImageIO.read(amber_index.class.getResource("/Amber_RESOURCES/AMBER_server.png")));
		Icon ikonka_klient = new ImageIcon(ImageIO.read(amber_index.class.getResource("/Amber_RESOURCES/AMBER_client.png")));
		
		//Panel
		
		JPanel ramka1 = new JPanel();
		ramka1.setBounds(150,150,500,300);
		ramka1.setBackground(Color.WHITE);
		ramka1.setBorder(border);
		ramka1.setOpaque(true);
		
		// Tekst
		
		JLabel autor = new JLabel("Copyright (c) by NoName_1000");
		autor.setBounds(10,530,185,40);
		autor.setForeground(Color.WHITE);
		autor.setHorizontalAlignment(JLabel.LEFT);
		
		JLabel wersja = new JLabel(Wersja);
		wersja.setBounds(600,530,185,40);
		wersja.setForeground(Color.WHITE);
		wersja.setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel tytul = new JLabel("TCP Chat v1.0");
		tytul.setFont(styl1);
		tytul.setBounds(270,43,300,68);
		tytul.setForeground(Color.BLACK);
		tytul.setBackground(Color.WHITE);
		tytul.setOpaque(true);
		tytul.setHorizontalAlignment(JLabel.CENTER);
		tytul.setBorder(border);
		
		JLabel tekst1 = new JLabel("Wybierz tryb pracy.");
		tekst1.setFont(styl2);
		tekst1.setBounds(155,155,485,40);
		tekst1.setForeground(Color.BLACK);
		tekst1.setBackground(Color.WHITE);
		tekst1.setOpaque(true);
		tekst1.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel tekst1_1 = new JLabel("");
		tekst1_1.setFont(styl2);
		tekst1_1.setBounds(155,195,485,40);
		tekst1_1.setForeground(Color.BLACK);
		tekst1_1.setBackground(Color.WHITE);
		tekst1_1.setOpaque(true);
		tekst1_1.setHorizontalAlignment(JLabel.CENTER);
		tekst1_1.setVisible(false);
		
		JLabel tekst1_2 = new JLabel("");
		tekst1_2.setFont(styl2);
		tekst1_2.setBounds(155,225,485,40);
		tekst1_2.setForeground(Color.BLACK);
		tekst1_2.setBackground(Color.WHITE);
		tekst1_2.setOpaque(true);
		tekst1_2.setHorizontalAlignment(JLabel.CENTER);
		tekst1_2.setVisible(false);
		
		JLabel tekst1_3 = new JLabel("");
		tekst1_3.setFont(styl2);
		tekst1_3.setBounds(155,235,485,40);
		tekst1_3.setForeground(Color.BLACK);
		tekst1_3.setBackground(Color.WHITE);
		tekst1_3.setOpaque(true);
		tekst1_3.setHorizontalAlignment(JLabel.CENTER);
		tekst1_3.setVisible(false);
		
		JLabel tekst2 = new JLabel("");
		tekst2.setFont(styl2);
		tekst2.setBounds(155,280,485,40);
		tekst2.setForeground(Color.BLACK);
		tekst2.setBackground(Color.WHITE);
		tekst2.setOpaque(true);
		tekst2.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel tekst3 = new JLabel("");
		tekst3.setFont(styl2);
		tekst3.setBounds(155,310,485,40);
		tekst3.setForeground(Color.BLACK);
		tekst3.setBackground(Color.WHITE);
		tekst3.setOpaque(true);
		tekst3.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel serwer_txt = new JLabel("Serwer");
		serwer_txt.setFont(styl2);
		serwer_txt.setBounds(220,330,100,30);
		serwer_txt.setHorizontalAlignment(JLabel.CENTER);
		serwer_txt.setOpaque(true);
		serwer_txt.setForeground(Color.BLACK);
		serwer_txt.setBackground(Color.WHITE);
		serwer_txt.setBorder(border);
		
		JLabel klient_txt = new JLabel("Klient");
		klient_txt.setFont(styl2);
		klient_txt.setBounds(480,330,100,30);
		klient_txt.setHorizontalAlignment(JLabel.CENTER);
		klient_txt.setOpaque(true);
		klient_txt.setForeground(Color.BLACK);
		klient_txt.setBackground(Color.WHITE);
		klient_txt.setBorder(border);
		
		JLabel port_txt = new JLabel(" Port: ");
		port_txt.setFont(styl2);
		port_txt.setBounds(220,210,89,30);
		port_txt.setBackground(Color.WHITE);
		port_txt.setForeground(Color.BLACK);
		port_txt.setBorder(border);
		port_txt.setOpaque(true);
		port_txt.setVisible(false);
		
		JLabel passwd_txt = new JLabel(" Hasło: ");
		passwd_txt.setFont(styl2);
		passwd_txt.setBounds(220,210,100,30);
		passwd_txt.setBackground(Color.WHITE);
		passwd_txt.setForeground(Color.BLACK);
		passwd_txt.setBorder(border);
		passwd_txt.setOpaque(true);
		passwd_txt.setVisible(false);
		
		JLabel address_txt = new JLabel(" Adres: ");
		address_txt.setFont(styl2);
		address_txt.setBounds(220,210,100,30);
		address_txt.setBackground(Color.WHITE);
		address_txt.setForeground(Color.BLACK);
		address_txt.setBorder(border);
		address_txt.setOpaque(true);
		address_txt.setVisible(false);
		
		JLabel message_txt = new JLabel(" Wiadomość: ");
		message_txt.setFont(styl2);
		message_txt.setBounds(213,338,150,30);
		message_txt.setBackground(Color.WHITE);
		message_txt.setForeground(Color.BLACK);
		message_txt.setBorder(border);
		message_txt.setOpaque(true);
		message_txt.setVisible(false);
		
		// Przyciski
		
		JButton serwer = new JButton("", ikonka_serwer);
		serwer.setOpaque(true);
		serwer.setFont(styl3);
		serwer.setHorizontalAlignment(JLabel.CENTER);
		serwer.setBounds(220,220,100,100);
		serwer.setBackground(Color.WHITE);
		serwer.setFocusPainted(false);
		
		JButton klient = new JButton("", ikonka_klient);
		klient.setOpaque(true);
		klient.setFont(styl3);
		klient.setHorizontalAlignment(JLabel.CENTER);
		klient.setBounds(480,220,100,100);
		klient.setBackground(Color.WHITE);
		klient.setFocusPainted(false);
		
		// Pole tekstowe
		
		JTextField numer_port = new JTextField();
		numer_port.setBounds(308,210,100,30);
		numer_port.setFont(styl2);
		numer_port.setBackground(Color.decode("#e9e9e9"));
		numer_port.setForeground(Color.BLACK);
		numer_port.setOpaque(true);
		numer_port.setVisible(false);
		numer_port.setBorder(border);
		
		JTextField numer_ip = new JTextField();
		numer_ip.setBounds(319,210,180,30);
		numer_ip.setFont(styl2);
		numer_ip.setBackground(Color.decode("#e9e9e9"));
		numer_ip.setForeground(Color.BLACK);
		numer_ip.setOpaque(true);
		numer_ip.setVisible(false);
		numer_ip.setBorder(border);
		
		JPasswordField aes_passwd = new JPasswordField();
		aes_passwd.setBounds(319,210,180,30);
		aes_passwd.setFont(styl2);
		aes_passwd.setBackground(Color.decode("#e9e9e9"));
		aes_passwd.setForeground(Color.BLACK);
		aes_passwd.setOpaque(true);
		aes_passwd.setVisible(false);
		aes_passwd.setBorder(border);
		
		JTextField wiadomosc = new JTextField();
		wiadomosc.setBounds(349,338,248,30);
		wiadomosc.setFont(styl2);
		wiadomosc.setBackground(Color.WHITE);
		wiadomosc.setForeground(Color.BLACK);
		wiadomosc.setOpaque(true);
		wiadomosc.setVisible(false);
		wiadomosc.setBorder(border);
		
		// Przyciski
		
		JButton next_port = new JButton("Dalej");
		next_port.setBounds(525,400,100,30);
		next_port.setVisible(false);
		next_port.setBackground(Color.GRAY);
		next_port.setForeground(Color.WHITE);
		next_port.setBorder(border);
		next_port.setFocusPainted(false);
		next_port.setVisible(false);
		
		JButton next_connect = new JButton("Dalej");
		next_connect.setBounds(525,400,100,30);
		next_connect.setVisible(false);
		next_connect.setBackground(Color.GRAY);
		next_connect.setForeground(Color.WHITE);
		next_connect.setBorder(border);
		next_connect.setFocusPainted(false);
		next_connect.setVisible(false);
		
		JButton next_connect2 = new JButton("Dalej");
		next_connect2.setBounds(525,400,100,30);
		next_connect2.setVisible(false);
		next_connect2.setBackground(Color.GRAY);
		next_connect2.setForeground(Color.WHITE);
		next_connect2.setBorder(border);
		next_connect2.setFocusPainted(false);
		next_connect2.setVisible(false);
		
		JButton send_msg = new JButton("Wyślij");
		send_msg.setBounds(525,400,100,30);
		send_msg.setVisible(false);
		send_msg.setBackground(Color.GRAY);
		send_msg.setForeground(Color.WHITE);
		send_msg.setBorder(border);
		send_msg.setFocusPainted(false);
		send_msg.setVisible(false);
		
		JButton send_msg2 = new JButton("Wyślij");
		send_msg2.setBounds(525,400,100,30);
		send_msg2.setVisible(false);
		send_msg2.setBackground(Color.GRAY);
		send_msg2.setForeground(Color.WHITE);
		send_msg2.setBorder(border);
		send_msg2.setFocusPainted(false);
		send_msg2.setVisible(false);
		
		JButton zamknij_port = new JButton("Zamknij port");
		zamknij_port.setBounds(420,400,100,30);
		zamknij_port.setBackground(Color.GRAY);
		zamknij_port.setForeground(Color.WHITE);
		zamknij_port.setFocusPainted(false);
		zamknij_port.setBorder(border);
		zamknij_port.setVisible(false);
		
		JButton zamknij_sesje = new JButton("Rozłącz się");
		zamknij_sesje.setBounds(420,400,100,30);
		zamknij_sesje.setBackground(Color.GRAY);
		zamknij_sesje.setForeground(Color.WHITE);
		zamknij_sesje.setFocusPainted(false);
		zamknij_sesje.setBorder(border);
		zamknij_sesje.setVisible(false);
		
		JButton cofnij = new JButton("Anuluj");
		cofnij.setBounds(420,400,100,30);
		cofnij.setBackground(Color.GRAY);
		cofnij.setForeground(Color.WHITE);
		cofnij.setFocusPainted(false);
		cofnij.setBorder(border);
		cofnij.setVisible(false);
		
		JButton connect_successfull = new JButton("");
		
		// Ramka
		
		chat_frame = new JTextArea();
		chat_frame.setBounds(110, 110, 550, 240);
		chat_frame.setEditable(false);
		chat_frame.setBackground(Color.decode("#e9e9e9"));
		chat_frame.setForeground(Color.BLACK);
		chat_frame.setFont(styl4);
		
		JScrollPane chat_frame_2 = new JScrollPane(chat_frame);
		ramka.getContentPane().add(chat_frame_2);
		chat_frame_2.setBounds(200, 210, 408, 120);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
				   chat_frame_2.getVerticalScrollBar().setValue(0);
			   }
			});
		chat_frame_2.setVisible(false);
		chat_frame_2.setBorder(border);
		
		ramka.add(serwer_txt);
		ramka.add(klient_txt);
		ramka.add(serwer);
		ramka.add(klient);
		ramka.add(numer_port);
		ramka.add(port_txt);
		ramka.add(next_port);
		ramka.add(next_connect);
		ramka.add(next_connect2);
		ramka.add(cofnij);
		ramka.add(zamknij_port);
		ramka.add(zamknij_sesje);
		ramka.add(chat_frame_2);
		ramka.add(wiadomosc);
		ramka.add(send_msg);
		ramka.add(send_msg2);
		ramka.add(message_txt);
		ramka.add(numer_ip);
		ramka.add(address_txt);
		ramka.add(aes_passwd);
		ramka.add(passwd_txt);
		ramka.add(tekst1);
		ramka.add(tekst1_1);
		ramka.add(tekst1_2);
		ramka.add(tekst1_3);
		ramka.add(tekst2);
		ramka.add(tekst3);
		ramka.add(logo);
		ramka.add(ramka1);
		ramka.add(tytul);
		ramka.add(autor);
		ramka.add(wersja);
		ramka.setVisible(true);
		
		
		// Eventy
		
		serwer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serwer.setVisible(false);
				klient.setVisible(false);
				serwer_txt.setVisible(false);
				klient_txt.setVisible(false);
				numer_port.setBounds(308,210,100,30);
				port_txt.setBounds(220,210,89,30);
				aes_passwd.setBounds(319,250,180,30);
				passwd_txt.setBounds(220,250,100,30);
				aes_passwd.setVisible(true);
				passwd_txt.setVisible(true);
				numer_port.setVisible(true);
				port_txt.setVisible(true);
				next_port.setVisible(true);
				cofnij.setVisible(true);
				
				tekst1.setText("Tryb serwera, wpisz numer port.");
				ramka.getRootPane().setDefaultButton(next_port);
			}
		});
		
		klient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serwer.setVisible(false);
				klient.setVisible(false);
				serwer_txt.setVisible(false);
				klient_txt.setVisible(false);
				address_txt.setVisible(true);
				numer_ip.setVisible(true);
				numer_port.setBounds(308,250,100,30);
				port_txt.setBounds(220,250,89,30);
				numer_port.setVisible(true);
				port_txt.setVisible(true);
				next_connect.setVisible(true);
				cofnij.setVisible(true);
				
				tekst1.setText("Tryb klienta, połącz się z serwerem.");
				ramka.getRootPane().setDefaultButton(next_connect);
			}
		});
		
		cofnij.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cofnij.setVisible(false);
				next_connect.setVisible(false);
				port_txt.setVisible(false);
				numer_port.setVisible(false);
				numer_ip.setVisible(false);
				address_txt.setVisible(false);
				next_port.setVisible(false);
				klient_txt.setVisible(true);
				serwer_txt.setVisible(true);
				klient.setVisible(true);
				serwer.setVisible(true);
				aes_passwd.setVisible(false);
				passwd_txt.setVisible(false);
				tekst1.setText("Wybierz tryb pracy.");
				numer_ip.setText("");
				numer_port.setText("");
				aes_passwd.setText("");
				tekst1_3.setText("");
				tekst1_3.setForeground(Color.BLACK);
				tekst1_3.setVisible(false);
				tekst2.setVisible(false);
				tekst2.setText("");
				tekst2.setForeground(Color.BLACK);
			}
		});
		
		next_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
				@Override
			    public void run() {
					next_connect.setEnabled(false);
					aes_passwd.setEnabled(false);
					cofnij.setEnabled(false);
					numer_port.setEnabled(false);
					numer_ip.setEnabled(false);
					
					if (ServerConnected == false && PasswordAuth == false) {
						tekst2.setForeground(Color.BLACK);
						tekst2.setText("Łączę się z serwerem...");
						tekst2.setVisible(true);
					
						try {
							tcp_connect = new Socket(numer_ip.getText(), Integer.parseInt(numer_port.getText()));
							tekst2.setVisible(false);
							tekst2.setText("");
							tekst2.setForeground(Color.BLACK);
							PasswordAuth = true;
							ramka.getRootPane().setDefaultButton(next_connect2);
						} catch (NumberFormatException e1) {
							tekst2.setVisible(true);
							tekst2.setText("W polu PORT musi być wpisana liczba!");
							tekst2.setForeground(Color.RED);
						} catch (UnknownHostException e1) {
							tekst2.setVisible(true);
							tekst2.setText("Nie udało się połączyć z serwerem!");
							tekst2.setForeground(Color.RED);
						} catch (IOException e1) {
							tekst2.setVisible(true);
							tekst2.setText("Nie udało się połączyć z serwerem!");
							tekst2.setForeground(Color.RED);
						}
						next_connect.setEnabled(true);
						cofnij.setEnabled(true);
						numer_port.setEnabled(true);
						numer_ip.setEnabled(true);
					}
				
					if (PasswordAuth == true) {
						try {
							tekst2.setForeground(Color.BLACK);
							tekst2.setText("Łączę się z serwerem...");
							tekst2.setVisible(true);
							DataOutputStream sendChallenge = new DataOutputStream(tcp_connect.getOutputStream());
							sendChallenge.writeUTF("[CHALLENGE]_PasswordHashPleaseResolv![0]");
							DataInputStream resolvChallenge = new DataInputStream(new BufferedInputStream(tcp_connect.getInputStream()));
							PasswordHash = resolvChallenge.readUTF();
							
							tekst1.setText("Wpisz hasło aby kontynuować.");
							port_txt.setVisible(false);
							numer_port.setVisible(false);
							address_txt.setVisible(false);
							numer_ip.setVisible(false);
							cofnij.setVisible(false);
							aes_passwd.setBounds(319,200,180,30);
							passwd_txt.setBounds(220,200,100,30);
							aes_passwd.setText("");
							aes_passwd.setVisible(true);
							aes_passwd.setEnabled(true);
							passwd_txt.setVisible(true);
							tekst2.setText("");
							tekst2.setVisible(false);
							next_connect.setVisible(false);
							next_connect2.setVisible(true);
							
						} catch (IOException e) {}
					}
				}
				}.start();
			}
		});
		
		next_connect2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
				@Override
				public void run() {
					tekst1_3.setForeground(Color.BLACK);
					tekst1_3.setVisible(true);
					tekst1_3.setText("Chwila...");
					aes_passwd.setEnabled(false);
					next_connect2.setEnabled(false);

					String decodePasswd = new String(aes_passwd.getPassword());
					String decrypt = amber_aes_engine.decrypt(PasswordHash, decodePasswd);
					if (decrypt.equals("[CHALLENGE]_Successfull[0]")) {
						password = decodePasswd;
						PasswordAuth = false;
						ServerConnected = true;
						aes_passwd.setVisible(false);
						passwd_txt.setVisible(false);
						next_connect2.setVisible(false);
						tekst1_3.setForeground(Color.BLACK);
						tekst1_3.setText("");
						tekst1_3.setVisible(false);
						connect_successfull.doClick();
					} else {
						tekst1_3.setForeground(Color.RED);
						tekst1_3.setText("Wpisano niepoprawne hasło!");
						tekst1_3.setVisible(true);
						next_connect2.setEnabled(true);
						aes_passwd.setEnabled(true);
						aes_passwd.setText("");
					}
				}
				}.start();
			}
		});
		
		connect_successfull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ServerConnected == true) {
					next_connect.setVisible(false);
					next_connect2.setVisible(false);
					port_txt.setVisible(false);
					numer_port.setVisible(false);
					address_txt.setVisible(false);
					numer_ip.setVisible(false);
					cofnij.setVisible(false);
					chat_frame_2.setVisible(true);
					wiadomosc.setVisible(true);
					send_msg2.setVisible(true);
					message_txt.setVisible(true);
					zamknij_sesje.setVisible(true);
					
					tekst1.setText("Chat między serwerem a klientem.");
					ramka.getRootPane().setDefaultButton(send_msg2);
					new Thread() {
					@Override
				    public void run() {
						chat_frame.setText("[SYSTEM] Połączono się z serwerem.");
						try {
							while (true) {
									DataInputStream odSerwera = new DataInputStream(new BufferedInputStream(tcp_connect.getInputStream()));
									String chat_decrypted = amber_aes_engine.decrypt(odSerwera.readUTF(), password);
									chat_write("[SERWER] " + chat_decrypted);
							}
						} catch (IOException e) {
							chat_frame_2.setVisible(false);
							wiadomosc.setVisible(false);
							send_msg2.setVisible(false);
							message_txt.setVisible(false);
							zamknij_sesje.setVisible(false);
							
							tekst1.setText("Serwer zamknął port");
							tekst1_1.setText("Możesz zamknąć ten program!");
							tekst1_1.setVisible(true);
							ServerConnected = false;
							ramka.getRootPane().setDefaultButton(null);
						}
					}
					}.start();
				}
			}
		});
		
		next_port.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					serwer_tcp = new ServerSocket(Integer.parseInt(numer_port.getText()));
					ServerOpened = true;
					tekst2.setText("");
					tekst2.setForeground(Color.BLACK);
					tekst2.setVisible(false);
					String decodePasswd = new String(aes_passwd.getPassword());
					password = decodePasswd;
				} catch (NumberFormatException e1) { 
					tekst2.setText("Musisz wpisać wartość liczbową!");
					tekst2.setForeground(Color.RED);
					tekst2.setVisible(true);
				} catch (IOException e1) {
					tekst2.setText("Nie udało się otworzyć portu!");
					tekst2.setForeground(Color.RED);
					tekst2.setVisible(true);
				}
				
				if (ServerOpened == true) {
					port_txt.setVisible(false);
					next_port.setVisible(false);
					numer_port.setVisible(false);
					chat_frame_2.setVisible(true);
					wiadomosc.setVisible(true);
					send_msg.setVisible(true);
					message_txt.setVisible(true);
					zamknij_port.setVisible(true);
					cofnij.setVisible(false);
					send_msg.setEnabled(false);
					aes_passwd.setVisible(false);
					passwd_txt.setVisible(false);
					
					tekst1.setText("Chat między serwerem a klientem.");
					ramka.getRootPane().setDefaultButton(null);
					new Thread() {
					@Override
				    public void run() {
						try {
							chat_frame.setText("[SYSTEM] Oczekuję na klienta...");
							connectionSC = serwer_tcp.accept();
							chat_write("[SYSTEM] Klient nawiązał połączenie z serwerem!");
							ramka.getRootPane().setDefaultButton(send_msg);
							send_msg.setEnabled(true);
							while (true) {
								DataInputStream odKlienta = new DataInputStream(new BufferedInputStream(connectionSC.getInputStream()));
								if (odKlienta.readUTF().equals("[CHALLENGE]_PasswordHashPleaseResolv![0]")) {
									String resolvV2 = amber_aes_engine.encrypt("[CHALLENGE]_Successfull[0]", password);
									DataOutputStream doKlienta = new DataOutputStream(connectionSC.getOutputStream());
									doKlienta.writeUTF(resolvV2);
								} else {
									String chat_decrypted = amber_aes_engine.decrypt(odKlienta.readUTF(), password);
									chat_write("[KLIENT] " + chat_decrypted);
								}
								if (ServerOpened == false) {
									connectionSC.close();
									serwer_tcp.close();
									return;
								}
							}
						} catch (IOException e1) { 
							chat_frame_2.setVisible(false);
							wiadomosc.setVisible(false);
							send_msg.setVisible(false);
							message_txt.setVisible(false);
							zamknij_port.setVisible(false);
							
							tekst1.setText("Klient zerwał połączenie!");
							tekst1_1.setText("Możesz zamknąć ten program!");
							tekst1_1.setVisible(true);
							ServerOpened = false;
							ramka.getRootPane().setDefaultButton(null);
						}
					}
					}.start();
				}
				
			}
		});
		
		send_msg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
				@Override
			    public void run() {
					try {
						if (!wiadomosc.getText().equals("")) {
							chat_write("[SERWER] " + wiadomosc.getText());
							DataOutputStream doKlienta = new DataOutputStream(connectionSC.getOutputStream());
							doKlienta.writeUTF(amber_aes_engine.encrypt(wiadomosc.getText(), password));
							wiadomosc.setText("");
						}
					} catch (IOException e1) {}
				}
				}.start();
			}
		});
		
		send_msg2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
				@Override
			    public void run() {
					try {
						if (!wiadomosc.getText().equals("")) {
							chat_write("[KLIENT] " + wiadomosc.getText());
							DataOutputStream doSerwera = new DataOutputStream(tcp_connect.getOutputStream());
							String encryptedReadyToSend = amber_aes_engine.encrypt(wiadomosc.getText(), password);
							doSerwera.writeUTF("");
							doSerwera.writeUTF(encryptedReadyToSend);
							wiadomosc.setText("");
						}
					} catch (IOException e1) {}
				}
				}.start();
			}
		});
		
		zamknij_port.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tekst1.setText("Port został zamknięty!");
					ServerOpened = false;
					connectionSC.close();
					serwer_tcp.close();
					chat_frame_2.setVisible(false);
					wiadomosc.setVisible(false);
					send_msg.setVisible(false);
					message_txt.setVisible(false);
					zamknij_port.setVisible(false);
					
					tekst1.setText("Port został zamknięty!");
					tekst1_1.setText("Możesz zamknąć ten program!");
					tekst1_1.setVisible(true);
					ramka.getRootPane().setDefaultButton(null);
				} catch (IOException e1) { }
			}
		});
		
		zamknij_sesje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ServerConnected = false;
					tcp_connect.close();
					chat_frame_2.setVisible(false);
					wiadomosc.setVisible(false);
					send_msg2.setVisible(false);
					message_txt.setVisible(false);
					zamknij_sesje.setVisible(false);
					
					tekst1.setText("Sesja została zamknięta!");
					tekst1_1.setText("Możesz zamknąć ten program!");
					tekst1_1.setVisible(true);
					ramka.getRootPane().setDefaultButton(null);
				} catch (IOException e1) { }
			}
		});
		
	}
	
	public static void chat_write(String add) {
		chat_frame.setText(chat_frame.getText() + "\n" + add);
		chat_frame.setCaretPosition(chat_frame.getDocument().getLength());
	}
	
}