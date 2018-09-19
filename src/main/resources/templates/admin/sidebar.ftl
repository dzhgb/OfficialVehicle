<!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="/theme_AdminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>Alexander Pierce</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>
            <!-- search form -->
            <#--<form action="#" method="get" class="sidebar-form">-->
                <#--<div class="input-group">-->
                    <#--<input type="text" name="q" class="form-control" placeholder="Search...">-->
                    <#--<span class="input-group-btn">-->
                <#--<button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>-->
                <#--</button>-->
              <#--</span>-->
                <#--</div>-->
            <#--</form>-->
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu" data-widget="tree" id="menu">
                <li class="header">数据管理</li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-users"></i> <span>用户管理</span>
                        <span class="pull-right-container">
                          <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li menu="user"><a href="/admin/user/list"><i class="fa fa-circle-o"></i> 用户管理</a></li>
                        <li menu="role"><a href="/admin/role/list"><i class="fa fa-circle-o"></i> 角色管理</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-users"></i> <span>车辆管理</span>
                        <span class="pull-right-container">
                          <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li menu=""><a href="#"><i class="fa fa-circle-o"></i> 查询记录</a></li>
                        <li menu=""><a href="#"><i class="fa fa-circle-o"></i> 维保记录</a></li>
                        <li menu=""><a href="#"><i class="fa fa-circle-o"></i> 公务用车</a></li>
                        <li menu=""><a href="#"><i class="fa fa-circle-o"></i> 事故/违章</a></li>
                        <li menu=""><a href="#"><i class="fa fa-circle-o"></i> 车辆基本信息</a></li>
                        <li menu=""><a href="#"><i class="fa fa-circle-o"></i> 巡检记录</a></li>
                        <li menu=""><a href="#"><i class="fa fa-circle-o"></i> 休假/代班</a></li>
                    </ul>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
