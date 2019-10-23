package com.skyblue.statemachine.test;

import com.skyblue.statemachine.config.simple.OrderEvents;
import com.skyblue.statemachine.config.simple.OrderStates;
import com.sun.jdi.ObjectReference;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br> 〈〉
 *
 * @author zhangjinmiao
 * @create 2019/10/17 15:13
 */
@Component
public class RoomMatchStateMachineHelper {

  private static final ObjectReference<RoomMatchStateMachineHelper> ref = new ObjectReference<RoomMatchStateMachineHelper>();

  /** 房间状态机参数传递通用DTO */
  public static final String ROOM_ACTION_DELIVERY_DTO = "room_action_delivery_dto";


  //    @Autowired
  //    private StateMachinePersist<RoomState, RoomEvent, String> stateMachinePersist;

  @Autowired
  private StateMachineService<OrderStates, OrderEvents> roomMatchStateMachineService;

  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @PostConstruct
  void init() {
    ref.set(this);
  }

  public static RoomMatchStateMachineHelper getInstance() {
    return ref.get();
  }

  /**
   * 获取状态机
   *
   * @param machineId
   *            状态机编号
   * @return
   */
  public StateMachine<OrderStates, OrderEvents> getStateMachine(String machineId) {
    return roomMatchStateMachineService.acquireStateMachine(machineId);
  }

  //    /**
  //     * 存储状态
  //     *
  //     * @param machineId
  //     *            状态机编号
  //     * @throws Exception
  //     */
  //    public void save(String machineId) throws Exception {
  //        StateMachineContext<RoomState, RoomEvent> stateMachineContext = stateMachinePersist.read(machineId);
  //        stateMachinePersist.write(stateMachineContext, machineId);
  //    }

  /**
   * 删除状态机
   *
   * @param machineId
   *            状态机编号
   */
  public void delete(String machineId) {
    roomMatchStateMachineService.releaseStateMachine(machineId);
    redisTemplate.delete("RedisRepositoryStateMachine:" + machineId);
  }

  /**
   * 普通状态转换事件
   *
   * @param machineId
   *            状态机编号
   * @param event
   *            事件
   */
  public StateMachine<OrderStates, OrderEvents> sendEvent(String machineId, OrderEvents event) {
    StateMachine<OrderStates, OrderEvents> stateMachine = getStateMachine(machineId);
    if (stateMachine.sendEvent(event)) {
      return stateMachine;
    }
    return null;
  }

  /**
   * 传参的状态转换事件
   *
   * @param machineId
   *            状态机编号
   * @param event
   *            事件
   * @param headerName
   *            传递参数的Key
   * @param object
   *            传递的参数：对象
   */
  public StateMachine<OrderStates, OrderEvents> sendEvent(String machineId, OrderEvents event, String headerName, Object object) {
    StateMachine<OrderStates, OrderEvents> stateMachine = getStateMachine(machineId);
    Message<OrderEvents> message = MessageBuilder
        .withPayload(event)
        .setHeader(headerName, object)
        .build();
    //传递参数的事件
    if (stateMachine.sendEvent(message)) {
      return stateMachine;
    }
    return null;
  }
}
