package adg.plugin;


import adg.plugin.actions.ValidateGraph;
import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;
import com.nomagic.magicdraw.plugins.Plugin;
import com.nomagic.magicdraw.ui.actions.DefaultDiagramAction;
import com.nomagic.uml2.impl.ElementsFactory;

import javax.swing.*;


public class ADG_Plugin extends Plugin{

    /**
     * Initializing the plugin.
     */
    @Override
    public void init()
    {
        JOptionPane.showMessageDialog(null, "STARTING ADG PLUGIN INITIALIZATION... YAHOO ON HOOD");

        // --> Add stereotypes for ADGs
        // this.init_stereotypes();

        // --> Add new diagram type
        Application.getInstance().addNewDiagramType(new ADG_Descriptor());

        // --> Add new diagram actions
        ActionsConfiguratorsManager.getInstance().addDiagramCommandBarConfigurator(
                ADG_Descriptor.ARCHITECTURE_DECISION_GRAPH,
                new AbstractActionConfigurator(new ValidateGraph())
        );


        // --> Success
        JOptionPane.showMessageDialog(null, "FINISHED ADG PLUGIN INITIALIZATION");
    }

    public void init_stereotypes(){
        System.out.println("--> INIT ADG STEREOTYPES");




        Project project = Application.getInstance().getProject();
        System.out.println((project == null));
        ElementsFactory elementsFactory = project.getElementsFactory();

    }


    @Override
    public boolean close()
    {
        return true;
    }

    @Override
    public boolean isSupported()
    {
        return true;
    }


//                    _    _
//        /\         | |  (_)
//       /  \    ___ | |_  _   ___   _ __   ___
//      / /\ \  / __|| __|| | / _ \ | '_ \ / __|
//     / ____ \| (__ | |_ | || (_) || | | |\__ \
//    /_/    \_\\___| \__||_| \___/ |_| |_||___/

    private static final class AbstractActionConfigurator implements AMConfigurator {

        private final DefaultDiagramAction action;

        AbstractActionConfigurator(DefaultDiagramAction action)
        {
            this.action = action;
        }

        @Override
        public void configure(ActionsManager mngr)
        {
            ActionsCategory category = new ActionsCategory();
            mngr.addCategory(category);
            category.addAction(this.action);
        }

        @Override
        public int getPriority()
        {
            return HIGH_PRIORITY;
        }
    }


}