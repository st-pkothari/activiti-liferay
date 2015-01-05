/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.engine.impl.cmd;

import java.io.Serializable;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ResourceEntity;


/**
 * @author Joram Barrez
 */
public class GetResourceCmd implements Command<ResourceEntity>, Serializable {
  
  private static final long serialVersionUID = 1L;
  protected String deploymentId;
  protected String resourceName;
  
  public GetResourceCmd(String deploymentId, String resourceName) {
    this.deploymentId = deploymentId;
    this.resourceName = resourceName;
  }

  public ResourceEntity execute(CommandContext commandContext) {
    if (deploymentId == null) {
      throw new ActivitiException("deploymentId is null");
    }
    if(resourceName == null) {
      throw new ActivitiException("resourceName is null");
    }
    
    ResourceEntity resource = commandContext
      .getResourceEntityManager()
      .findResourceByDeploymentIdAndResourceName(deploymentId, resourceName);
    return resource;
  }
  
}
