//构造的api系统，用于外部调用
package eu.mihosoft.freerouting.gui;


import eu.mihosoft.freerouting.gui.BoardFrame;
import eu.mihosoft.freerouting.interactive.ScreenMessages.IMsgListener;
import eu.mihosoft.freerouting.interactive.BoardHandling;


class MainApplicationAPI implements IMsgListener{
    
    public MainApplicationAPI(BoardFrame main_frame){
        board_frame=main_frame;
        //注册行动器
        handling=main_frame.board_panel.board_handling;
        //注册监听器
        handling.screen_messages.set_listener(this);     
    }

    public void set_msg(String msg){
        System.out.print("\nmsg:^"+msg+"^");
        if(msg.startsWith("Postroute completed")){
            //自动布线完成，开始保存ses
            System.out.print("\nsave ses");
            board_frame.design_file.write_specctra_session_file(board_frame);
        }
    }

    public void api_dsn2ses(){
        handling.start_batch_autorouter();
        //Postroute completed
    }

    BoardHandling handling;//行动器
    BoardFrame board_frame;
}
