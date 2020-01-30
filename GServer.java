import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

class GServer  extends WindowAdapter implements ActionListener,MouseListener
{
ServerSocket ss;
Socket s;
Frame f;
Panel p;
TextArea ta1,ta2;
Button send,cls1,cls2;
Font ft,ft1;
Label l1;

GServer()
{
try
{
System.out.println("Waiting for others to join...");
ss=new ServerSocket(2019);
s=ss.accept();
System.out.println("Connection Established!!!");
f=new Frame();
ta1=new TextArea();
ta2=new TextArea();
p=new Panel();
cls1=new Button("Clear recieved message");
cls2=new Button("Clear sent message");
ta2.setText("Enter your message here");
ta1.setBackground(new Color(160,219,222));
ta1.setForeground(new Color(159,26,63));
ta2.setBackground(new Color(159,166,45));
ta2.setForeground(new Color(159,26,63));
ft=new Font("arial",2,24);
ft1=new Font("arial",2,20);
send =new Button("SEND=>");
send.setFont(new Font("Monotype Corsiva",2,36));
send.setBackground(new Color(219,219,134));
ta1.setFont(ft);
ta2.setFont(ft1);
f.setLayout(null);
ta1.setBounds(10,30,600,320);
ta2.setBounds(10,450,600,100);
p.setBounds(10,550,600,50);
f.add(p);
p.add(cls1);
p.add(cls2);
send.setBounds(10,350,600,100);
f.add(ta1);
f.add(ta2);
f.add(send);
send.addActionListener(this);
cls1.addActionListener(this);
cls2.addActionListener(this);
ta2.addMouseListener(this);
f.addWindowListener(this);
f.setSize(600,600);
f.setVisible(true);
while(true)
{
ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
ta1.setText(ta1.getText()+"\n"+"Friend:"+ois.readObject());
}
}
catch(Exception ex)
{
System.out.println(ex.getMessage());
}
}
public void windowClosing(WindowEvent e)
{
f.dispose();
}
public void actionPerformed(ActionEvent e)
{
Button b=new Button();
b=(Button)e.getSource();
if(b==send)
{
ta1.setText(ta1.getText()+"\n"+"YOU:"+ta2.getText());
try
{
ObjectOutputStream oos =new ObjectOutputStream(s.getOutputStream());
oos.writeObject(ta2.getText());
}
catch(Exception ex)
{
System.out.println(ex.getMessage());
}

ta2.setText("");

}
if(b==cls1)
{
ta1.setText("");
}
if(b==cls2)
{
ta2.setText("");
}

}
public void mousePressed(MouseEvent e)
{
ta2.setText("");
}
public void mouseExited(MouseEvent e)
{
}
public void mouseEntered(MouseEvent e)
{
}
public void mouseReleased(MouseEvent e)
{
}
public void mouseClicked(MouseEvent e)
{
}







public static void main(String ar[])
{
new GServer(); 
}
}