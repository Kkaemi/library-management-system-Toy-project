package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.DefaultListModel;

class ChatHandlerObject extends Thread {
	private ObjectInputStream reader;
	private ObjectOutputStream writer;
	private Socket socket;
	private List<ChatHandlerObject> list;
	private LibraryDB librarydb = new LibraryDB();
	private DbDTO dbDTO = new DbDTO();
	private int seat;

	private List<DbDTO> Dlist;

	private DefaultListModel<DbDTO> model;

	public ChatHandlerObject(Socket socket, List<ChatHandlerObject> list) throws IOException {
		this.socket = socket;
		this.list = list;

		writer = new ObjectOutputStream(socket.getOutputStream());
		reader = new ObjectInputStream(socket.getInputStream());
	}

	public void run() {
		InfoDTO infoDTO = null;

		while (true) {
			try {
				// 클라이언트로부터 받기
				// 닉네임
				infoDTO = (InfoDTO) reader.readObject();

				if (infoDTO.getCommand() == Info.JOIN) {

					List<DbDTO> dlist = new LibraryDB().getLibDB();

					for (DbDTO dbdto : dlist) {
						InfoDTO sendDTO = new InfoDTO();

//					
						sendDTO.setSeatNum(dbdto.getSeatNum());
						sendDTO.setSeatCheck(dbdto.getSeatCheck());
//						System.out.println(dbdto.getSeatNum());
//						System.out.println(dbdto.getSeatCheck());

						broadcast(sendDTO);

					}

				} else if (infoDTO.getSeatCheck() == 1) {// 클릭
					seat = infoDTO.getSeatNum();

					// 모든 클라이언트들에게
					System.out.println("야호");

					InfoDTO sendDTO = new InfoDTO();

					sendDTO.setSeatCheck(2);
					sendDTO.setSeatNum(seat);

					librarydb.clickToDB(sendDTO);

					broadcast(sendDTO);

					System.out.println("야호2");
				} else if (infoDTO.getSeatCheck() == -1) {
					seat = infoDTO.getSeatNum();
					System.out.println("야호3");

					InfoDTO sendDTO = new InfoDTO();

					sendDTO.setSeatCheck(-1);
					sendDTO.setSeatNum(seat);

					System.out.println("야호4");
					librarydb.clickToDB(sendDTO);
					broadcast(sendDTO);

				} else if (infoDTO.getCommand() == Info.EXIT) {
					InfoDTO sendDTO = new InfoDTO();

					// 나가려고 exit를 보낸 클라이언트에게 답변 보내기
					sendDTO.setCommand(Info.EXIT);
					writer.writeObject(sendDTO);
					writer.flush();

					reader.close();
					writer.close();
					socket.close();

					break;

				}

			} catch (IOException io) {
				io.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} // while
	}

	public void broadcast(InfoDTO sendDTO) {
		for (ChatHandlerObject handler : list) {
			try {
				handler.writer.writeObject(sendDTO);
				handler.writer.flush();
			} catch (IOException io) {
				io.printStackTrace();
			}
		} // for
	}
}
