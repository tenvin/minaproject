package com.twg.service;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;
import java.util.Date;

/**
 * Created by twg on 2017/3/20.
 */
public class MinaService {
    public static void main(String[] args) {

        IoAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast("logger",new LoggingFilter());
        acceptor.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        acceptor.setHandler(new DemoServerHandler());

        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);

        try{
            acceptor.bind(new InetSocketAddress(9123));
        }catch (Exception e){

        }
    }

    private static class DemoServerHandler implements IoHandler {
        public void sessionCreated(IoSession ioSession) throws Exception {

        }

        public void sessionOpened(IoSession ioSession) throws Exception {

        }

        public void sessionClosed(IoSession ioSession) throws Exception {

        }

        public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {

        }

        public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {

        }

        public void messageReceived(IoSession ioSession, Object o) throws Exception {
            String str = o.toString();
            Date date = new Date();
            ioSession.write(date.toString());

            String ip = ioSession.getRemoteAddress().toString();
            System.out.println("===> Message From " + ip +" : " + str);
            ioSession.write("Hello " + str);
        }

        public void messageSent(IoSession ioSession, Object o) throws Exception {

        }

        public void inputClosed(IoSession ioSession) throws Exception {

        }
    }
}
