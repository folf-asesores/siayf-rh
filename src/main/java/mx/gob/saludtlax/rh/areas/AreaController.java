package mx.gob.saludtlax.rh.areas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import mx.gob.saludtlax.rh.areas.AreaDTO;
import mx.gob.saludtlax.rh.areas.Area2EJB;
import mx.gob.saludtlax.rh.excepciones.BusinessException;

@ManagedBean(name = "area")
@SessionScoped
public class AreaController implements Serializable {
//*
	/**
	 * 
	 
	

	private TreeNode root;
	private TreeNode selectedNode;

	private Integer numeroArea;
	private String nombreArea;

	private List<TreeNode> listaNodos;
	private List<TreeNode> listaExpand;

	private String orientacion;
	AreaDTO areaDTO = new AreaDTO();

	private AreaView areaView;

	private boolean value1;
	private boolean value2;

	@Inject
	Area2EJB areaEJB;

	@PostConstruct
	public void init() {
		this.areaView = new AreaView();
		inabilitar();
		this.orientacion = "vertical";
		listaNodos = new ArrayList<>();
		listaExpand = new ArrayList<>();
		buildTree();
	}

	public void buildTree() {
		root = new DefaultTreeNode("Organigrama", null);
		for (AreaDTO areaDTO : areaEJB.listaArea()) {
			loadBranch(areaDTO, root);
		}
	}

	public void loadBranch(AreaDTO areaDTO, TreeNode root) {
		TreeNode treeNode = new DefaultTreeNode(areaDTO, root);
		for (AreaDTO subArea : areaDTO.getCuentas()) {
			treeNode.setExpanded(isExpanded(treeNode));
			listaNodos.add(treeNode);
			loadBranch(subArea, treeNode);
		}
	}

	private boolean isExpanded(TreeNode treeNode) {
		for (TreeNode item : listaExpand) {
			if (((AreaDTO) treeNode.getData()).getId().equals(((AreaDTO) item.getData()).getId())) {
				return true;
			}
		}
		return false;
	}

	public void agregarArea() {
		areaDTO = (AreaDTO) selectedNode.getData();
		if (selectedNode != null) {
			//
			// AreaDTO c = new AreaDTO();
			// c.setNombre(nombreArea);
			// c.setAreaPadre(areaDTO.getId());
			// areaEJB.registrar(c);
			// for (TreeNode lista : listaNodos) {
			// if (lista.isExpanded()) {
			// listaExpand.add(lista);
			// }
			// }
			try {
				AreaDTO area = areaView.getAreaDTO();
				area.setAreaPadre(areaDTO.getId());
				areaEJB.registrar(area);
				for (TreeNode lista : listaNodos) {
					if (lista.isExpanded()) {
						listaExpand.add(lista);
					}
				}
				buildTree();
				this.listaExpand.clear();
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area",
						"Información Guardada Correctamente");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
				inabilitar();
			} catch (BusinessException ex) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"Error al Guardar Información");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
			for (TreeNode lista : listaNodos) {
				if (lista.isExpanded()) {
					listaExpand.add(lista);
				}
			}

			buildTree();
			this.listaExpand.clear();
		}
	}

	public void actualizarArbol() {
		areaDTO = (AreaDTO) selectedNode.getData();
		if (selectedNode != null) {

			// areaDTO.setNombre(nombreArea);
			// areaEJB.actualizar(areaDTO);
			try {
				AreaDTO area = areaView.getEditarAreaDTO();
				areaEJB.actualizar(area);
				for (TreeNode lista : listaNodos) {
					if (lista.isExpanded()) {
						listaExpand.add(lista);
					}
				}
				buildTree();
				this.listaExpand.clear();
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area",
						"Información Actualizada Correctamente");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			} catch (BusinessException ex) {
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
						"Error al Actualizar Información");
				FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			}
		}
	}

	public void eliminarArea() {
		areaDTO = (AreaDTO) selectedNode.getData();
		try {
			areaEJB.eliminar(areaDTO.getId());
			if (selectedNode != null) {
				selectedNode.getChildren().clear();
				selectedNode.getParent().getChildren().remove(selectedNode);
				selectedNode.setParent(null);
				selectedNode = null;
			}
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Area",
					"Información Eliminada Correctamente");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		} catch (BusinessException ex) {
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Error al Eliminar Información");
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
		}
	}

	public void habilitar() {
		areaView.habilitar();
	}

	public void inabilitar() {
		areaView.inabilitar();
	}

	public void onNodeExpand(NodeExpandEvent event) {
		areaView.inabilitar();
		System.out.println("Se expandio el arbol::");
		event.getTreeNode().setExpanded(true);
	}

	public void onNodeCollapse(NodeCollapseEvent event) {
		areaView.inabilitar();
		System.out.println("Se cerro el arbol::");
		event.getTreeNode().setExpanded(false);
	}

	public void onNodeSelect(NodeSelectEvent event) {
		// activar los botones
		habilitar();
		areaDTO = (AreaDTO) selectedNode.getData();
		AreaDTO area = new AreaDTO();
		area = areaEJB.obtenerAreId(areaDTO.getId());
		areaView.setEditarAreaDTO(area);
		areaView.setDetailsAreaDTO(area);
		System.out.println("Se ha seleccionado el nodo::" + event.getTreeNode().toString());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());
		FacesContext.getCurrentInstance().addMessage(null, message);

		if (this.selectedNode != null) {
			this.selectedNode.setSelected(false);
		}
		this.selectedNode = event.getTreeNode();
		event.getTreeNode().setSelected(true);
	}

	public void onNodeUnselect(NodeUnselectEvent event) {
		event.getTreeNode().setSelected(false);
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public List<TreeNode> getListaNodos() {
		return listaNodos;
	}

	public void setListaNodos(List<TreeNode> listaNodos) {
		this.listaNodos = listaNodos;
	}

	public List<TreeNode> getListaExpand() {
		return listaExpand;
	}

	public void setListaExpand(List<TreeNode> listaExpand) {
		this.listaExpand = listaExpand;
	}

	public Integer getNumeroArea() {
		return numeroArea;
	}

	public void setNumeroArea(Integer numeroArea) {
		this.numeroArea = numeroArea;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

	public boolean isValue1() {
		return value1;
	}

	public void setValue1(boolean value1) {
		this.value1 = value1;
	}

	public boolean isValue2() {
		return value2;
	}

	public void setValue2(boolean value2) {
		this.value2 = value2;
	}

	public void addMessage() {
		String aux = "";
		if (isValue1()) {
			aux = "vertical";
			orientacion = aux;
		} else {
			if (isValue2()) {
				aux = "horizontal";
				orientacion = aux;
			} else {
				aux = "vertical";
				orientacion = aux;
			}
		}
		System.out.println("posicion:: " + aux);
	}

	public AreaView getAreaView() {
		return areaView;
	}

	public void setAreaView(AreaView areaView) {
		this.areaView = areaView;
	}
	*/
}
