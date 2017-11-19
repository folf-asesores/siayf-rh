
package mx.gob.saludtlax.rh.areas;

// @Stateless
public class Area2EJB {
    /*
     * @Inject
     * AreasAdscripcionRepository query;
     *
     * @Resource
     * EJBContext context;
     *
     * /**
     *
     * @param areaDTO
     *
     * public void registrar(AreaDTO areaDTO) {
     * AreaAdscripcionEntity areaEntity = new AreaAdscripcionEntity();
     * UserTransaction userTransaction = context.getUserTransaction();
     * try {
     * userTransaction.begin();
     * areaEntity.setNombre(areaDTO.getNombre());
     * areaEntity.setAreaPadre(areaDTO.getAreaPadre());
     * areaEntity.setDescripcion(areaDTO.getDescripcion());
     * areaEntity.setTitular(areaDTO.getTitular());
     * query.crear(areaEntity);
     * userTransaction.commit();
     * } catch (PersistenceException ex) {
     * throw new BusinessException("Error al registrar");
     * } catch (Exception ex) {
     * try {
     * userTransaction.rollback();
     * } catch (Exception e) {
     * throw new RuntimeException(e);
     * }
     * ex.printStackTrace();
     * }
     * }
     *
     * /**
     *
     * @param areaDTO
     *
     * public void actualizar(AreaDTO areaDTO) {
     * AreaAdscripcionEntity areaEntity = new AreaAdscripcionEntity();
     * UserTransaction userTransaction = context.getUserTransaction();
     * try {
     * userTransaction.begin();
     * areaEntity.setId(areaDTO.getId());
     * areaEntity.setNombre(areaDTO.getNombre());
     * areaEntity.setAreaPadre(areaDTO.getAreaPadre());
     * areaEntity.setDescripcion(areaDTO.getDescripcion());
     * areaEntity.setTitular(areaDTO.getTitular());
     * query.editar(areaEntity);
     * userTransaction.commit();
     * } catch (PersistenceException ex) {
     * throw new BusinessException("Error al editar");
     * } catch (Exception ex) {
     * try {
     * userTransaction.rollback();
     * } catch (Exception e) {
     * throw new RuntimeException(e);
     * }
     * ex.printStackTrace();
     * }
     * }
     *
     * /**
     *
     * @param id
     *
     * public void eliminar(Integer id) {
     * UserTransaction userTransaction = context.getUserTransaction();
     * try {
     * userTransaction.begin();
     * query.eliminar(id);
     * userTransaction.commit();
     * } catch (NoResultException ex) {
     * throw new BusinessException("No hay registro que eliminar");
     * } catch (Exception e) {
     * try {
     * userTransaction.rollback();
     * } catch (Exception ex) {
     * throw new RuntimeException();
     * }
     * e.printStackTrace();
     * }
     * }
     * /**
     *
     * @return
     *
     * public List<AreaDTO> listaArea(){
     * List<AreaDTO> listaDTO = new ArrayList<AreaDTO>();
     * UserTransaction userTransaction = context.getUserTransaction();
     * try{
     * userTransaction.begin();
     * List<AreaAdscripcionEntity> listaEntity = query.obtenerRegistros();
     * for(AreaAdscripcionEntity entity : listaEntity){
     * listaDTO.add(loadBranch(entity));
     * }
     * userTransaction.commit();
     * return listaDTO;
     * }catch(NoResultException ex){
     * throw new BusinessException("No hay datos que mostrar");
     * }
     * catch (Exception ex) {
     * try {
     * userTransaction.rollback();
     * } catch (Exception e) {
     * throw new RuntimeException(e);
     * }
     * ex.printStackTrace();
     * }
     * return listaDTO;
     * }
     * /**
     *
     * @param areaEntity
     *
     * @return
     *
     * public AreaDTO loadBranch(AreaAdscripcionEntity areaEntity) {
     * AreaDTO areaDTO = new AreaDTO();
     * areaDTO.setId(areaEntity.getId());
     * areaDTO.setNombre(areaEntity.getNombre());
     * areaDTO.setAreaPadre(areaEntity.getAreaPadre());
     * areaDTO.setDescripcion(areaEntity.getDescripcion());
     * areaDTO.setTitular(areaEntity.getTitular());
     * List<AreaDTO> listCuentaDTO = new ArrayList<AreaDTO>();
     * for (AreaAdscripcionEntity subcuenta : areaEntity.getCuentas()) {
     * listCuentaDTO.add(loadBranch(subcuenta));
     * }
     * areaDTO.setCuentas(listCuentaDTO);
     * return areaDTO;
     * }
     * /**
     *
     * public AreaDTO obtenerAreId(Integer id){
     * AreaDTO areaDTO = new AreaDTO();
     * UserTransaction userTransaction = context.getUserTransaction();
     * try{
     * userTransaction.begin();
     * AreaAdscripcionEntity areaEntity = query.obtenerAreaId(id);
     * areaDTO.setId(areaEntity.getId());
     * areaDTO.setNombre(areaEntity.getNombre());
     * areaDTO.setAreaPadre(areaEntity.getAreaPadre());
     * areaDTO.setDescripcion(areaEntity.getDescripcion());
     * areaDTO.setTitular(areaEntity.getTitular());
     * userTransaction.commit();
     * return areaDTO;
     * }catch(NoResultException ex){
     * throw new BusinessException("No hay registro que mostrar");
     * }catch (Exception ex) {
     * try {
     * userTransaction.rollback();
     * } catch (Exception e) {
     * throw new RuntimeException(e);
     * }
     * ex.printStackTrace();
     * }
     * return areaDTO;
     * }
     */
}
