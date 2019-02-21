package com.amazonaws.lambda;

import java.util.List;
import java.util.Optional;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.Volume;

/**
 * lambda function
 *
 * @author jason lin 2019/02/20 20:13
 */
public class Handler {

    /**
     * Lambda处理程序
     * Handler
     * @param params
     */
    public void handler(Params params) {
        //创建EC2客户端
        AmazonEC2 ec2 = createEC2Client(params.getAccessKey(), params.getSecretKey(), params.getRegion());
        //获取所有EBS VOLUME
        //get all the volumes
        DescribeVolumesResult describeVolumesResult = ec2.describeVolumes();
        Optional<List<Volume>> volumesResult = Optional.ofNullable(describeVolumesResult.getVolumes());
        //打印所有volume id
        //print all the volume id
        volumesResult.ifPresent(volumes -> volumes.forEach(volume -> {
            System.out.println(volume.getVolumeId());
        }));
        //创建快照
        //create snapshot
        createSnapshot(ec2, params.getVolumeId(), params.getDescription());
    }

    /**
     * create an ec2 client
     *
     * @param accessKey access key
     * @param secretKey secret key
     * @param region    region
     * @return ec2
     */
    private AmazonEC2 createEC2Client(String accessKey, String secretKey, String region) {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(credentials);
        return AmazonEC2ClientBuilder.standard().withRegion(region).withCredentials(awsCredentialsProvider).build();
    }

    /**
     * 创建EBS 卷快照
     *
     * @param ec2         ec2
     * @param volumeId    卷id
     * @param description 描述
     * @return CreateSnapshotResult
     */
    private CreateSnapshotResult createSnapshot(AmazonEC2 ec2, String volumeId, String description) {
        CreateSnapshotRequest request = new CreateSnapshotRequest(volumeId, description);
        return ec2.createSnapshot(request);
    }
}
