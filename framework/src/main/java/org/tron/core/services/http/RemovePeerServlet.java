package org.tron.core.services.http;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tron.core.Wallet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j(topic = "API")
public class RemovePeerServlet extends RateLimiterServlet {
  @Autowired
  private Wallet wallet;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      String peerAddress = request.getParameter("peer");
      wallet.removePeer(peerAddress);
      response.getWriter().println("{}");
    } catch (Exception e) {
      Util.processError(e, response);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      PostParams params = PostParams.getPostParams(request);
      JSONObject jsonObject = JSONObject.parseObject(params.getParams());
      String peerAddress = jsonObject.getString("peer");
      wallet.removePeer(peerAddress);
      response.getWriter().println("{}");
    } catch (Exception e) {
      Util.processError(e, response);
    }
  }
}
