package com.vms.mapper;

import java.util.List;

import com.vms.model.Resource;
import com.vms.utils.Param;
import com.vms.utils.RedisCache;

public interface ResourceMapper
{
    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    int insert(Resource resource);
    
    /**
     * 修改资源
     *
     * @param resource
     * @return
     */
    int updateResource(Resource resource);
    
    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    int deleteResourceById(Long id);
    
    /**
     * 查询菜单资源
     *
     * @param resourceType
     * @param pid
     * @return
     */
    List<Resource> findResourceAllByTypeAndPid(@Param ("resourceType") Integer resourceType, @Param ("pid") Long pid);
    
    /**
     * 查询菜单资源
     *
     * @param resourceType
     * @param pid
     * @return
     */
    //@RedisCache(key="'findResourceAllByPid_'+#pid",expire = -1,fieldKey="resource",nameSpace="portal")
    List<Resource> findResourceAllByPid(@Param ("pid") Long pid);
    
    /**
     * 查询所有资源
     *
     * @return
     */
    //@RedisCache(key="findResourceAll",expire = -1,fieldKey="resource",nameSpace="portal")
    List<Resource> findResourceAll();
    
    /**
     * 查询所有一级菜单
     * @return
     */
    //@RedisCache(key="findResourceAllOne",expire = -1,fieldKey="resource",nameSpace="portal")
    List<Resource> findResourceAllOne();
    
    /**
     * 查询一级资源
     *
     * @param resourceMenu
     * @return
     */
    //@RedisCache(key="'findResourceAllByTypeAndPidNull_'#resourceMenu",expire = -1,fieldKey="resource",nameSpace="portal")
    List<Resource> findResourceAllByTypeAndPidNull(@Param ("resourceMenu") Integer resourceMenu);
    
    /**
     * 根据id查询资源
     *
     * @param id
     * @return
     */
    //@RedisCache(key="'findResourceById_'#id",expire = -1,fieldKey="resource",nameSpace="portal")
    Resource findResourceById(@Param ("id") Long id);
    
    /**
     * 获取一级菜单
     * 
     * @param userId
     */
    @RedisCache (key = "'findResourceByOne_'+#userId", expire = 120, fieldKey = "resource", nameSpace = "portal")
    List<Resource> findResourceByOne(@Param ("userId") String userId);
    
    /**
     * 获取二级菜单
     * 
     * @param userId,pid
     */
    //@RedisCache(key="'findResourceByTwo_'+#resource.userId+'_pid_'+#resource.pid",expire = -1,fieldKey="resource",nameSpace="portal")
    List<Resource> findResourceByTwo(@Param ("resource") Resource resource);
    
    /**
     * 获取三级菜单
     * 
     * @param userId
     */
    //@RedisCache(key="'findResourceByThree_'+#resource.userId+'_pid_'+#resource.pid",expire = -1,fieldKey="resource",nameSpace="portal")
    List<Resource> findResourceByThree(@Param ("resource") Resource resource);
    
    /**
     * 根据用户和PID获取菜单
     * 
     * @param userId
     */
    @RedisCache (key = "'findResourceByPidAndUser_'+#resource.userId+'_pid_'+#resource.pid", expire = 120, fieldKey = "resource", nameSpace = "portal")
    List<Resource> findResourceByPidAndUser(@Param ("resource") Resource resource);
    
    /**
     * 根据用户获取菜单
     * 
     * @param userId
     */
    List<Resource> findResourceByPidAndUserAll(@Param ("resource") Resource resource);
    
    /**
     * 查询可授权资源
     *
     * @return
     */
    List<Resource> findGrantResources();
    
    /**
     * 查询名称  (缓存24H)
     */
    @RedisCache (key = "'queryResourceById_'+#id", expire = 86400, fieldKey = "resource", nameSpace = "portal")
    Resource queryResourceById(@Param ("id") long id);
    
    List<Resource> findQuickResourceByUser(String userId);
    
    List<Resource> findQuickResourceByRole(Long roleId);
    
    int delHomeRsourceByUser(String userId);
    
    int delHomeRsourceByRole(String roleId);
    
    int addHomeUserResource(Resource rs);
    
    int addOprHomeRoleResource(Resource rs);
    
    List<Long> findHomeResourceIdListByUser(String userId);
    
    List<Long> findOprHomeResourceIdListByUser(String userId);
    
    int delOprHomeRsourceByUser(String userId);
    
    int addOprHomeUserResource(Resource rs);
    
    Resource getOprHomeResourceByUser(String userId);
    
    Resource getOprHomeResourceByRole(String roleId);
    
    List<Long> findUserPanelResourceIdListByUser(String userId);
    
    int delPanelRsourceByUser(String userId);
    
    int addUserPanelResource(Resource rs);
    
    /**
     * 根据快捷操作码查询菜单
     *
     * @return
     */
    Resource queryResourceByMenuCode(String menuCode);
    
    @RedisCache (key = "'findAllResourceByUser_'+#userId", expire = 120, fieldKey = "resource", nameSpace = "portal")
    List<Resource> findAllResourceByUser(@Param ("userId") String userId);
}